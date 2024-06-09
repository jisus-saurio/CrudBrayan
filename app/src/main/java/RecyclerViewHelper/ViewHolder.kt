package RecyclerViewHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stanlyy.arce.stanlyyduenasappcrudd.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val txtCard = view.findViewById<TextView>(R.id.txt_productoCard)

}