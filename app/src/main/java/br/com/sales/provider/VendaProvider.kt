package br.com.sales.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.net.Uri
import br.com.sales.database.DatabaseHelper
import br.com.sales.model.Venda

class VendaProvider : ContentProvider() {

    companion object {
        val PROVIDER_NAME: String = "br.com.sales.VendaProvider"
        val CONTENT_URI: Uri = Uri.parse("content://$PROVIDER_NAME").buildUpon().appendPath(Venda.TABLE_NAME).build()

        val ALL = 1
        val READ_BY_ID = 2

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    lateinit var database: SQLiteDatabase

    init {
        uriMatcher.addURI(PROVIDER_NAME, "sales", ALL)
        uriMatcher.addURI(PROVIDER_NAME, "sales/#", READ_BY_ID)
    }

    override fun onCreate(): Boolean {
        database = DatabaseHelper(context).writableDatabase
        return database != null
    }

    override fun getType(uri: Uri): String? {

        return when (uriMatcher.match(uri)) {
            ALL -> "vnd.android.cursor.dir/${Venda.TABLE_NAME}"
            READ_BY_ID -> "vnd.android.cursor.item/${Venda.TABLE_NAME}"
            else -> throw IllegalArgumentException("URI nao existe $uri")
        }

    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        val cursor: Cursor = when (uriMatcher.match(uri)) {
            ALL -> database.query(
                Venda.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )

            READ_BY_ID -> {
                val selection = "${Venda.TABLE_NAME} = ?"
                val selectionArgs = arrayOf(ProvidersUtil.idFromUri(uri) as String)

                database.query(
                    Venda.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }

            else -> throw IllegalArgumentException("Uri nao encontrada")
        }

        cursor.setNotificationUri(context.contentResolver, uri)

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = database.insert(Venda.TABLE_NAME, null, values)

        if (id > 0) {
            val newUri = ContentUris.withAppendedId(CONTENT_URI, id)
            context.contentResolver.notifyChange(newUri, null)
            return newUri
        }

        throw SQLiteException("Falha ao registrar nova venda")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {

        val rows = when (uriMatcher.match(uri)) {

            ALL -> database.update(Venda.TABLE_NAME, values, selection, selectionArgs)

            READ_BY_ID -> {
                val selection = Venda.ID_COLUMN + " = ?"
                val selectionArgs: Array<String> = arrayOf(ProvidersUtil.idFromUri(uri) as String)

                database.update(Venda.TABLE_NAME, values, selection, selectionArgs)
            }

            else -> 0
        }

        if (rows > 0) {
            context.contentResolver.notifyChange(uri, null)
        }

        return rows

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        val rows = when (uriMatcher.match(uri)) {

            ALL -> database.delete(Venda.TABLE_NAME, selection, selectionArgs)

            READ_BY_ID -> {
                val selection = Venda.ID_COLUMN + " = ?"
                val selectionArgs: Array<String> = arrayOf(ProvidersUtil.idFromUri(uri) as String)

                database.delete(Venda.TABLE_NAME, selection, selectionArgs)
            }

            else -> 0
        }

        if (rows > 0) {
            context.contentResolver.notifyChange(uri, null)
        }

        return rows

    }


}