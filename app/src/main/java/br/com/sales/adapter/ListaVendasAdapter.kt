package br.com.sales.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.sales.R
import br.com.sales.model.Venda
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ListaVendasAdapter(val context: Context, val vendas: ArrayList<Venda>) :
    RecyclerView.Adapter<CardVendaViewHolder>() {

    override fun getItemCount(): Int = vendas.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardVendaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_venda_layout, parent, false)
        return CardVendaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardVendaViewHolder?, position: Int) {
        val venda: Venda = vendas[position]

        holder?.let {
            it.id!!.text = venda.id.toString()
            it.description!!.text = venda.descricao
            it.vendaAddition!!.text = "%.2f".format(venda.acrescimo)
            it.vendaDiscount!!.text = "%.2f".format(venda.desconto)
            it.vendaTotal!!.text = "%.2f".format(venda.total)
            it.vendaDate!!.text = SimpleDateFormat("dd/MM/yyyy").format(venda.data)
        }
    }
}

class CardVendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val id: TextView = itemView.findViewById(R.id.venda_id)
    val description: TextView = itemView.findViewById(R.id.venda_description)
    val vendaAddition: TextView = itemView.findViewById(R.id.venda_addition)
    val vendaDiscount: TextView = itemView.findViewById(R.id.venda_discount)
    val vendaTotal: TextView = itemView.findViewById(R.id.venda_total)
    val vendaDate: TextView = itemView.findViewById(R.id.venda_date)
}