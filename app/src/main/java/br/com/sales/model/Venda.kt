package br.com.sales.model

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

data class Venda(val id: Long) {

    lateinit var descricao: String
    lateinit var total: BigDecimal
    lateinit var acrescimo: BigDecimal
    lateinit var desconto: BigDecimal
    lateinit var data: Date
    var sync: Boolean = false

    companion object Schema {
        val TABLE_NAME = "venda"

        val ID_COLUMN = "id"
        val DESCRICAO_COLUMN = "descricao"
        val TOTAL_COLUMN = "total"
        val ACRESCIMO_COLUMN = "acrescimo"
        val DESCONTO_COLUMN = "desconto"
        val DATA_COLUMN = "data"
        val SYNC_COLUMN = "sync"

        fun formatData(data: Date): String = SimpleDateFormat("dd/MM/yyyy").format(data)

        fun parseData(data: String): Date = SimpleDateFormat("dd/MM/yyyy").parse(data)
    }
}