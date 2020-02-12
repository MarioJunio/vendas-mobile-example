package br.com.sales.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.sales.model.Venda

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_VENDA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Venda.TABLE_NAME}")
        onCreate(db)
    }
}

val DB_NAME = "sales"
val VERSION = 1

val CREATE_TABLE_VENDA = """
    CREATE TABLE ${Venda.TABLE_NAME} (
        ${Venda.ID_COLUMN} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Venda.DESCRICAO_COLUMN} TEXT NOT NULL,
        ${Venda.TOTAL_COLUMN} NUMERIC(10,2) NOT NULL,
        ${Venda.ACRESCIMO_COLUMN} NUMERIC(10,2),
        ${Venda.DESCONTO_COLUMN} NUMERIC(10,2),
        ${Venda.DATA_COLUMN} TEXT,
        ${Venda.SYNC_COLUMN} INTEGER)
""".trimIndent()