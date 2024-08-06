package RecyclerViewHelpers

import android.media.Image
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thomas.galdamez.proyectoformativoo.R


class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //En el viewholder mando a llamar a los elementos de la card
    val txtNombrePacientes = view.findViewById<TextView>(R.id.txtPacientes)
    val imgBorrar = view.findViewById<ImageView>(R.id.imgBorrar)
    val imgEditar = view.findViewById<ImageView>(R.id.imgEditar)

}