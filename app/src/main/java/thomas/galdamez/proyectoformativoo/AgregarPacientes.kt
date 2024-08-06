package thomas.galdamez.proyectoformativoo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import java.util.UUID

class AgregarPacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAtras = findViewById<ImageButton>(R.id.btnVolver)
        btnAtras.setOnClickListener {
            val intent = Intent(this, pacientes::class.java)
            startActivity(intent)
        }

        //1- mando a llamar a todos los elementos de la vista
        val txtNombres = findViewById<TextView>(R.id.txtNombres)
        val txtApellidos = findViewById<TextView>(R.id.txtApellidos)
        val txtEdad = findViewById<TextView>(R.id.txtEdad)
        val txtEnfermedad = findViewById<TextView>(R.id.txtEnfermedad)
        val txtNumeroHabitacion = findViewById<TextView>(R.id.txtNumeroHabitacion)
        val txtNumeroCama = findViewById<TextView>(R.id.txtNumeroCama)
        val txtFechaNacimiento = findViewById<TextView>(R.id.txtFechaNacimiento)
        val txtReceta = findViewById<TextView>(R.id.txtReceta)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        //2- Programo el boton de guardar
        btnGuardar.setOnClickListener {

            // Guardo en una variable los valores que escribió el usuario
            val nombres = txtNombres.text.toString()
            val apellidos = txtApellidos.text.toString()
            val edad = txtEdad.text.toString()
            val enfermedad = txtEnfermedad.text.toString()
            val numeroHabitacion = txtNumeroHabitacion.text.toString()
            val numeroCama = txtNumeroCama.text.toString()
            val fechaNacimiento = txtFechaNacimiento.text.toString()
            val receta = txtReceta.text.toString()


            // Variable para verificar si hay errores, la inicializamos en false
            var hayErrores = false

            //1-
            // Validar que los campos no estén vacíos

            if (nombres.isEmpty()) {
                txtNombres.error = "Los nombres son obligatorios"
                hayErrores = true
            }
            else {
                txtNombres.error = null
            }

            if (apellidos.isEmpty()) {
                txtApellidos.error = "Los apellidos son obligatorios"
                hayErrores = true
            }
            else {
                txtApellidos.error = null
            }

            if (edad.isEmpty()) {
                txtEdad.error = "La edad es obligatorio"
                hayErrores = true
            }
            else {
                txtEdad.error = null
            }

            if (enfermedad.isEmpty()) {
                txtEnfermedad.error = "La enfermedad es obligatoria"
                hayErrores = true
            }
            else {
                txtEnfermedad.error = null
            }

            if (numeroHabitacion.isEmpty()) {
                txtNumeroHabitacion.error = "La habitacion es obligatoria"
                hayErrores = true
            }
            else {
                txtNumeroHabitacion.error = null
            }

            if (numeroCama.isEmpty()) {
                txtNumeroCama.error = "El numero de cama es obligatorio"
                hayErrores = true
            }
            else {
                txtNumeroCama.error = null
            }

            if (fechaNacimiento.isEmpty()) {
                txtFechaNacimiento.error = "El numero de cama es obligatorio"
                hayErrores = true
            }
            else {
                txtFechaNacimiento.error = null
            }

            if (receta.isEmpty()) {
                txtReceta.error = "El numero de cama es obligatorio"
                hayErrores = true
            }
            else {
                txtReceta.error = null
            }

            if (hayErrores) {
                Toast.makeText(this@AgregarPacientes, "Datos ingresados incorrectamente", Toast.LENGTH_SHORT).show()
                //1-Crear un objeto de la clase conexion
            } else {
                val intent: Intent = Intent(this, pacientes::class.java)
                startActivity(intent)

                CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()

                //2-Crear una variable que contenga un PrepareStatement

                val addPaciente = objConexion?.prepareStatement("insert into tbPacientes (uuid_Pacientes, Nombres, Apellidos, Edad, Enfermedad, Numero_Habitacion, Numero_Cama, Fecha_Nacimiento, Receta) values(?,?,?,?,?,?,?,?,?)")!!
                addPaciente.setString(1,UUID.randomUUID().toString())
                addPaciente.setString(2,txtNombres.text.toString())
                addPaciente.setString(3,txtApellidos.text.toString())
                addPaciente.setInt(4,txtEdad.text.toString().toInt())
                addPaciente.setString(5,txtEnfermedad.text.toString())
                addPaciente.setString(6,txtNumeroHabitacion.text.toString())
                addPaciente.setString(7,txtNumeroCama.text.toString())
                addPaciente.setString(8,txtFechaNacimiento.text.toString())
                addPaciente.setString(9,txtReceta.text.toString())
                addPaciente.executeUpdate()

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AgregarPacientes, "Paciente agregado", Toast.LENGTH_SHORT).show()
                        txtNombres.setText("")
                        txtApellidos.setText("")
                        txtEdad.setText("")
                        txtEnfermedad.setText("")
                        txtNumeroHabitacion.setText("")
                        txtNumeroCama.setText("")
                        txtFechaNacimiento.setText("")
                        txtReceta.setText("")
                    }
                }
            }
        }
    }
}