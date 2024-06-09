package Modelo

import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class DataClassTicket {

    data class DataClassTicket(
        val numTicket: Int,
        val titulo: String,
        val descripcion: String,
        val autor: String,
        val correo: String,
        val fechaCreacion: Date,
        val estado: String,
        val fechaFinalizacion: Date?
    )


}