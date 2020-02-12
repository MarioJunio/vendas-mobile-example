package br.com.sales.model

import java.math.BigDecimal
import java.util.*

data class Venda(val id: Long, val descricao: String, val total: BigDecimal, val acrescimo: BigDecimal, val desconto: BigDecimal, val data: Date, val sync: Boolean) {

    companion object Schema {
        val TABLE_NAME = "venda"

        val ID_COLUMN = "id"
        val DESCRICAO_COLUMN = "descricao"
        val TOTAL_COLUMN = "total"
        val ACRESCIMO_COLUMN = "acrescimo"
        val DESCONTO_COLUMN = "desconto"
        val DATA_COLUMN = "data"
        val SYNC_COLUMN = "sync"
    }
}