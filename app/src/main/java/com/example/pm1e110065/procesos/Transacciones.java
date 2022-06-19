package com.example.pm1e110065.procesos;

public class Transacciones {
    //nombre de la base de datos
    public static final String NameDataBase = "DBPM1";

    //Creacion de tablas de la BD
    public static final String tablaContactos = "contactos";

    //escoger los campos
    public static final String id = "id";
    public static final String pais = "pais";
    public static final String nombres = "nombres";
    public static final String telefono = "telefono";
    public static final String nota = "nota";

    //hacer las sentencias sql para crear tabla

    public static final String CreateTableContactos = "CREATE TABLE contactos " +
        "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pais TEXT, nombres TEXT, telefono INTEGER,"+
            "nota TEXT)";

    //comando para eliminar tabla
    public static final String DropTableContactos = "DROP TABLE IF EXISTS contactos";
}
