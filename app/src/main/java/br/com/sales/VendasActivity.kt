package br.com.sales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import br.com.sales.adapter.ListaVendasAdapter
import br.com.sales.model.Venda

class VendasActivity : AppCompatActivity() {

    lateinit var listaVendas: RecyclerView
    lateinit var listaVendasAdapter: ListaVendasAdapter
    val vendas: ArrayList<Venda> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendas)

        listaVendasAdapter = ListaVendasAdapter(this, vendas)

        listaVendas = findViewById(R.id.vendas)
        listaVendas.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        listaVendas.adapter = listaVendasAdapter

    }
}
