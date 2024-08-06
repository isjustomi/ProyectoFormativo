package thomas.galdamez.proyectoformativoo

import RecyclerViewHelpers.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbPacientes

class pacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgregar = findViewById<ImageButton>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarPacientes::class.java)
            startActivity(intent)
        }

        val rcvPacientes = findViewById<RecyclerView>(R.id.rcvPacientes)

        //Agrego un layout al rcv
        rcvPacientes.layoutManager = LinearLayoutManager(this)

        fun obtenerPacientes(): List<tbPacientes>{

            //Creo un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //Creo un statement
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM tbPacientes")!!

            val listaPacientes = mutableListOf<tbPacientes>()

            while (resultSet.next()){

                val uuid = resultSet.getString("uuid_Pacientes")
                val Nombres = resultSet.getString("Nombres")
                val Apellidos = resultSet.getString("Apellidos")
                val Edad = resultSet.getInt("Edad")
                val Enfermedad = resultSet.getString("Enfermedad")
                val numeroHabitacion = resultSet.getInt("Numero_Habitacion")
                val numeroCama = resultSet.getInt("Numero_Cama")
                val fechaNacimiento = resultSet.getString("Fecha_Nacimiento")
                val Receta = resultSet.getString("Receta")

                val valoresJuntos = tbPacientes(uuid, Nombres, Apellidos, Edad, Enfermedad, numeroHabitacion, numeroCama, fechaNacimiento, Receta)

                listaPacientes.add(valoresJuntos)
            }
            return listaPacientes
        }

        //asignar adaptador al rcv
        CoroutineScope(Dispatchers.IO).launch {
            val pacientesDB = obtenerPacientes()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(pacientesDB)
                rcvPacientes.adapter = adapter
            }
        }
    }
}