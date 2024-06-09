package RecyclerViewHelper

import Modelo.DataClassTicket
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adaptador {

    class Adaptador(private var Datos: List<DataClassTicket>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



        fun actualizarLista(nuevaLista:List<DataClassTicket>){
            Datos=nuevaLista
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }



}