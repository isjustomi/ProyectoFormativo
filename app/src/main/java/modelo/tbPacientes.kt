package modelo

data class tbPacientes(
    val uuid_Pacientes: String,
    val Nombres: String,
    val Apellidos: String,
    val Edad: Int,
    val Enfermedad: String,
    val Numero_Habitacion: Int,
    val Numero_Cama: Int,
    val Fecha_Nacimiento: String,
    val Receta: String
)
