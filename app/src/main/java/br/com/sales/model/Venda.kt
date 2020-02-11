package br.com.sales.model

import java.time.LocalDate

data class Venda(val id: Int, val descricao: String, val total: Double, val acrescimo: Double, val desconto: Double, val data: LocalDate) {

    companion object Schema {
        val TABLE_NAME = "venda"

        val ID_COLUMN = "id"
        val DESCRICAO_COLUMN = "descricao"
        val TOTAL_COLUMN = "total"
        val ACRESCIMO_COLUMN = "acrescimo"
        val DESCONTO_COLUMN = "desconto"
        val DATA_COLUMN = "data"
    }
}