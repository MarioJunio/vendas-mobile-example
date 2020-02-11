package br.com.sales.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import br.com.sales.database.DatabaseHelper
import br.com.sales.model.Venda

class VendaProvider : ContentProvider() {

    companion object {
        val authority: String = "br.com.sales.provider"

        val ALL = 1
        val READ_BY_ID = 2

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    lateinit var writer: SQLiteDatabase

    init {
        uriMatcher.addURI(authority, "sales", ALL)
        uriMatcher.addURI(authority, "sales/#", READ_BY_ID)
    }

    override fun onCreate(): Boolean {
        val databaseHelper = DatabaseHelper(context)
        writer = databaseHelper.writableDatabase

        return writer != null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = writer.insert(Venda.TABLE_NAME, null, values)

        if (id > 0) {
            ContentUris.withAppendedId()
        }

    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
    }

    override fun getType(uri: Uri): String? {

        when (uriMatcher.match(uri)) {
            ALL -> "sales.com.br.venda/all"
            READ_BY_ID -> "sales.com.br.venda/read_by_id"
            else -> throw IllegalArgumentException("URI nao existe $uri")
        }

    }

}