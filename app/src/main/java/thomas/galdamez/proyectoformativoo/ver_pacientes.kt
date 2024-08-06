package thomas.galdamez.proyectoformativoo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ver_pacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnAtras = findViewById<ImageButton>(R.id.imgVolver)
        btnAtras.setOnClickListener {
            val intent = Intent(this, pacientes::class.java)
            startActivity(intent)
        }

        val UUISRecibido = intent.getStringExtra("uuid_Pacientes")
        val NombresRecibido = intent.getStringExtra("Nombres")
        val ApellidosRecibido = intent.getStringExtra("Apellidos")
        val EdadRecibido = intent.getIntExtra("Edad", 0)
        val EnfermedadRecibido = intent.getStringExtra("Enfermedad")
        val NumeroHabitacionRecibido = intent.getIntExtra("Numero_Habitacion", 0)
        val NumeroCamaRecibido = intent.getIntExtra("Numero_Cama", 0)
        val FechaNacimientoRecibido = intent.getStringExtra("Fecha_Nacimiento")
        val RecetaRecibido = intent.getStringExtra("Receta")

        val textViewNombres = findViewById<TextView>(R.id.textViewNombres)
        val textViewApellidos = findViewById<TextView>(R.id.textViewApellidos)
        val textViewEdad = findViewById<TextView>(R.id.textViewEdad)
        val textViewEnfermedad = findViewById<TextView>(R.id.textViewEnfermedad)
        val textViewNumeroHabitacion = findViewById<TextView>(R.id.textViewNumeroHabitacion)
        val textViewNumeroCama = findViewById<TextView>(R.id.textViewNumeroCama)
        val textViewFechaNacimiento = findViewById<TextView>(R.id.textViewFechaNacimiento)
        val textViewReceta = findViewById<TextView>(R.id.textViewReceta)

        textViewNombres.text = NombresRecibido
        textViewApellidos.text = ApellidosRecibido
        textViewEdad.text = EdadRecibido.toString()
        textViewEnfermedad.text = EnfermedadRecibido
        textViewNumeroHabitacion.text =NumeroHabitacionRecibido.toString()
        textViewNumeroCama.text =NumeroCamaRecibido.toString()
        textViewFechaNacimiento.text =FechaNacimientoRecibido
        textViewReceta.text = RecetaRecibido

    }
}