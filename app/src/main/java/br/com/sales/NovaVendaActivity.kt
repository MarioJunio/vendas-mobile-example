package br.com.sales

import android.content.ContentValues
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.sales.model.Venda
import br.com.sales.provider.VendaProvider
import java.util.*

class NovaVendaActivity : AppCompatActivity() {

    lateinit var inpDescription: EditText
    lateinit var inpTotal: EditText
    lateinit var inpAddition: EditText
    lateinit var inpDiscount: EditText
    lateinit var textTotalReal: TextView
    lateinit var btnSalvarNovaVenda: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_venda)

        inpDescription = findViewById(R.id.form_venda_description)
        inpTotal = findViewById(R.id.form_venda_total)
        inpAddition = findViewById(R.id.form_venda_addition)
        inpDiscount = findViewById(R.id.form_venda_discount)
        textTotalReal = findViewById(R.id.form_venda_total_real)
        btnSalvarNovaVenda = findViewById(R.id.btn_salvar_nova_venda)
    }

    fun salvarVenda(view: View) {

        val values = ContentValues()
        values.put(Venda.DESCRICAO_COLUMN, inpDescription.text.toString())
        values.put(Venda.TOTAL_COLUMN, inpTotal.text.toString().toDouble())
        values.put(Venda.ACRESCIMO_COLUMN, inpAddition.text.toString().toDouble())
        values.put(Venda.DESCONTO_COLUMN, inpDiscount.text.toString().toDouble())
        values.put(Venda.DATA_COLUMN, Venda.formatData(Date()))
        values.put(Venda.SYNC_COLUMN, 0)

        contentResolver.insert(VendaProvider.CONTENT_URI, values)

        Log.v("Nova venda", "Salva")
    }
}
