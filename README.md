# ProyectoFormativo
CREATE TABLE tbPacientes (
    uuid_Pacientes VARCHAR2(50)PRIMARY KEY NOT NULL,
    Nombres VARCHAR2(100) NOT NULL,
    Apellidos VARCHAR2(100) NOT NULL,
    Edad NUMBER NOT NULL,
    Enfermedad VARCHAR2(100) NOT NULL,
    Numero_Habitacion NUMBER NOT NULL,
    Numero_Cama NUMBER NOT NULL,
    Fecha_Nacimiento VARCHAR2(50) NOT NULL,
    Receta VARCHAR2(300) NOT NULL
);