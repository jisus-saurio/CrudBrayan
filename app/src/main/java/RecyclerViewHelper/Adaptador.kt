package RecyclerViewHelper

import Modelo.DataClassTicket
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import stanlyy.arce.stanlyyduenasappcrudd.R

    class Adaptador(var Datos: List<DataClassTicket>) : RecyclerView.Adapter<RecyclerViewHelper.ViewHolder>() {



        fun actualizarLista(nuevaLista:List<DataClassTicket>){
            Datos=nuevaLista
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHelper.ViewHolder {
            val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_ticket, parent, false)
            return RecyclerViewHelper.ViewHolder(vista)
        }

        override fun getItemCount() = Datos.size

        override fun onBindViewHolder(holder: RecyclerViewHelper.ViewHolder, position: Int) {
            val item  = Datos[position]
            holder.txtCard.text = item.titulo
        }

    }



