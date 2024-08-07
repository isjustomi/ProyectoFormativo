package modelo

data class tbPacientes(
    var uuid_Pacientes: String,
    var Nombres: String,
    var Apellidos: String,
    var Edad: Int,
    var Enfermedad: String,
    var Numero_Habitacion: Int,
    var Numero_Cama: Int,
    var Fecha_Nacimiento: String,
    var Receta: String
)
