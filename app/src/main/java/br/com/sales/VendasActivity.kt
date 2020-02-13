package br.com.sales

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import br.com.sales.adapter.ListaVendasAdapter
import br.com.sales.model.Venda
import br.com.sales.provider.VendaProvider
import java.math.BigDecimal

class VendasActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var listaVendas: RecyclerView
    lateinit var listaVendasAdapter: ListaVendasAdapter
    val vendas: ArrayList<Venda> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendas)

        listaVendasAdapter = ListaVendasAdapter(this, vendas)

        listaVendas = findViewById(R.id.vendas)
        listaVendas.layoutManager = LinearLayoutManager(this)
        listaVendas.adapter = listaVendasAdapter
    }

    override fun onResume() {
        super.onResume()
        LoaderManager.getInstance(this).initLoader(1, null, this)
    }

    fun navigateNovaVenda(view: View) {
        startActivity(Intent(this, NovaVendaActivity::class.java))
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        val uri: Uri = VendaProvider.CONTENT_URI
        return CursorLoader(this, uri, null, null, null, null)
    }

    override fun onLoadFinished(p0: Loader<Cursor>, cursor: Cursor?) {
        vendas.clear()

        cursor?.let {

            if (it.moveToFirst()) {

                do {
                    val venda = Venda(it.getLong(it.getColumnIndex(Venda.ID_COLUMN)))
                    venda.descricao = it.getString(it.getColumnIndex(Venda.DESCRICAO_COLUMN))
                    venda.acrescimo =
                        BigDecimal(it.getDouble(it.getColumnIndex(Venda.ACRESCIMO_COLUMN)))
                    venda.desconto =
                        BigDecimal(it.getDouble(it.getColumnIndex(Venda.DESCONTO_COLUMN)))
                    venda.total = BigDecimal(it.getDouble(it.getColumnIndex(Venda.TOTAL_COLUMN)))
                    venda.data = Venda.parseData(it.getString(it.getColumnIndex(Venda.DATA_COLUMN)))
                    venda.sync =
                        if (it.getInt(it.getColumnIndex(Venda.SYNC_COLUMN)) == 1) true else false

                    vendas.add(venda)

                } while (it.moveToNext())

            }

        }

    }

    override fun onLoaderReset(p0: Loader<Cursor>) {
    }
}
