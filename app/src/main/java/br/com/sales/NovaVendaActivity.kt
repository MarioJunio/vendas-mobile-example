package br.com.sales

import android.content.ContentValues
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.sales.provider.VendaProvider

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

        val values: ContentValues = ContentValues()
        values.put()

    }
}
