package RecyclerViewHelpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import modelo.tbPacientes
import thomas.galdamez.proyectoformativoo.R
import thomas.galdamez.proyectoformativoo.ver_pacientes

class Adaptador(var Datos: List<tbPacientes>): RecyclerView.Adapter <ViewHolder>() {

    fun EliminarRegistro(nombrePaciente: String, posicion: Int) {
        //Notificar al adaptador
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()

            val deleteUsuario = objConexion?.prepareStatement("delete tbPacientes where Nombres = ?")!!
            deleteUsuario.setString(1,nombrePaciente)
            deleteUsuario.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        //Quito los datos de la lista
        notifyItemRemoved(posicion)
        //notificamos el cambio para que refresque la lista
        notifyDataSetChanged()
    }

    //Editar
    fun editarDatos(nuevoNombre: String, nuevoApellido: String, nuevoEdad: Int, nuevoEnfermedad: String, nuevoHabitacion: Int, nuevoCama: Int, nuevoFecha: String, nuevoReceta: String, uuid_Pacientes: String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()

            val updateArbitro = objConexion?.prepareStatement("update tbPacientes set Nombres =?, Apellidos =?, Edad =?, Enfermedad =?, Numero_Habitacion =?, Numero_Cama =?, Fecha_Nacimiento =?, Receta =? where uuid_Pacientes =?")!!
            updateArbitro.setString(1, nuevoNombre)
            updateArbitro.setString(2, nuevoApellido)
            updateArbitro.setInt(3, nuevoEdad)
            updateArbitro.setString(4, nuevoEnfermedad)
            updateArbitro.setInt(5, nuevoHabitacion)
            updateArbitro.setInt(6, nuevoCama)
            updateArbitro.setString(7, nuevoFecha)
            updateArbitro.setString(8, nuevoReceta)
            updateArbitro.setString(9, uuid_Pacientes)
            updateArbitro.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit?.executeUpdate()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card,parent,false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtNombrePacientes.text = item.Nombres

        holder.imgBorrar.setOnClickListener {
            val contexto = holder.txtNombrePacientes.context

            val builder = AlertDialog.Builder(contexto)
            builder.setTitle("Eliminar")
            builder.setMessage("Estas seguro que deseas eliminar?")

            //Botones de alerta
            builder.setPositiveButton("Si"){
                dialog, wich ->
                EliminarRegistro(item.Nombres, position)
            }
            builder.setNegativeButton("No"){
                    dialog, wich ->
                dialog.dismiss()
            }
            builder.show()
        }

        holder.imgEditar.setOnClickListener {
            val context = holder.itemView.context
            val builder = android.app.AlertDialog.Builder(context)

            builder.setTitle("Editar Paciente")

            val txtNuevoNombre = EditText(context).apply {
                setHint(item.Nombres)
            }
            val txtNuevoApellido = EditText(context).apply {
                setHint(item.Apellidos)
            }
            val txtNuevaEdad = EditText(context).apply {
                setHint(item.Edad.toString())
            }
            val txtNuevoEnfermedad = EditText(context).apply {
                setHint(item.Enfermedad)
            }
            val txtNuevoHabitacion = EditText(context).apply {
                setHint(item.Numero_Habitacion)
            }
            val txtNuevoCama = EditText(context).apply {
                setHint(item.Numero_Cama)
            }
            val txtNuevoFecha = EditText(context).apply {
                setHint(item.Fecha_Nacimiento)
            }
            val txtNuevoReceta = EditText(context).apply {
                setHint(item.Receta)
            }

            val layout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                addView(txtNuevoNombre)
                addView(txtNuevoApellido)
                addView(txtNuevaEdad)
                addView(txtNuevoEnfermedad)
                addView(txtNuevoHabitacion)
                addView(txtNuevoCama)
                addView(txtNuevoFecha)
                addView(txtNuevoReceta)
            }
            builder.setView(layout)

            builder.setPositiveButton("Guardar"){
                    dialog, wich -> editarDatos(txtNuevoNombre.text.toString(), txtNuevoApellido.text.toString(), txtNuevaEdad.text.toString().toInt(), txtNuevoEnfermedad.text.toString(), txtNuevoHabitacion.text.toString().toInt(), txtNuevoCama.text.toString().toInt(), txtNuevoFecha.text.toString(), txtNuevoReceta.text.toString(), item.uuid_Pacientes)
            }
            builder.setNegativeButton("Cancelar"){
                    dialog, wich -> dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val pantallaVer = Intent(context, ver_pacientes::class.java)
            pantallaVer.putExtra("uuid_Pacientes", item.uuid_Pacientes)
            pantallaVer.putExtra("Nombres", item.Nombres)
            pantallaVer.putExtra("Apellidos", item.Apellidos)
            pantallaVer.putExtra("Edad", item.Edad)
            pantallaVer.putExtra("Enfermedad", item.Enfermedad)
            pantallaVer.putExtra("Numero_Habitacion", item.Numero_Habitacion)
            pantallaVer.putExtra("Numero_Cama", item.Numero_Cama)
            pantallaVer.putExtra("Fecha_Nacimiento", item.Fecha_Nacimiento)
            pantallaVer.putExtra("Receta", item.Receta)
            context.startActivity(pantallaVer)
        }
    }
}