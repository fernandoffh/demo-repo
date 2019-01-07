package basedatos.sqlite;

public class Tools {

//    encuesta
    public static final String NOMBRE_TABLA_ENCUESTA = "encuesta";
    public static final String COLUM_ID_ENCUESTA_P = "id";
    public static final String COLUM_ID_ENCUESTA_L = "id_encuesta";
    public static final String COLUM_NOMBRE_ENCUESTA = "nombre";
    public static final String COLUM_DESC_ENCUESTA = "descripcion";

    public static final String TABLA_ENCUESTA = ""
            + "CREATE TABLE " + NOMBRE_TABLA_ENCUESTA + " ("
            + "" + COLUM_ID_ENCUESTA_L + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "" + COLUM_ID_ENCUESTA_P + " INTEGER NOT NULL,"
            + "" + COLUM_NOMBRE_ENCUESTA + " TEXT NOT NULL,"
            + "" + COLUM_DESC_ENCUESTA + " TEXT NOT NULL"
            + ");";

//    tipo pregunta
    public static final String NOMBRE_TABLA_TIPO_PREGUNTA = "tipo_pregunta";
    public static final String COLUM_ID_TABLA_TIPO_PREGUNTA_P = "id";
    public static final String COLUM_ID_TABLA_TIPO_PREGUNTA_L = "id_tipo_pregunta";
    public static final String COLUM_TIPO_TIPO_PREGUNTA = "tipo";

    public static final String TABLA_TIPO_PREGUNTA = ""
            + "CREATE TABLE " + NOMBRE_TABLA_TIPO_PREGUNTA + " ("
            + "" + COLUM_ID_TABLA_TIPO_PREGUNTA_L + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "" + COLUM_ID_TABLA_TIPO_PREGUNTA_P + " INTEGER NOT NULL, "
            + "" + COLUM_TIPO_TIPO_PREGUNTA + " TEXT NOT NULL"
            + ");";

//    pregunta
    public static final String NOMBRE_TABLA_PREGUNTA = "pregunta";
    public static final String COLUM_ID_PREGUNTA_L = "id_pregunta";
    public static final String COLUM_ID_PREGUNTA_P = "id";
    public static final String COLUM_PREGUNTA = "pregunta";
    public static final String COLUM_TIPO_RESPUESTA = "tipo_respuesta";
    public static final String COLUM_LONGITUD_RESPUESTA = "longitud_respuesta";
    public static final String COLUM_ENCUESTA_ID = "encuesta_id";
    public static final String COLUM_TIPO_PREGUNTA_ID = "tipo_pregunta_id";

    public static final String TABLA_PREGUNTA = ""
            + "CREATE TABLE " + NOMBRE_TABLA_PREGUNTA + " ("
            + "" + COLUM_ID_PREGUNTA_L + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "" + COLUM_ID_PREGUNTA_P + " INTEGER NOT NULL, "
            + "" + COLUM_PREGUNTA + " TEXT NOT NULL, "
            + "" + COLUM_TIPO_RESPUESTA + " TEXT NOT NULL, "
            + "" + COLUM_LONGITUD_RESPUESTA + " INTEGER NOT NULL, "
            + "" + COLUM_ENCUESTA_ID + " INTEGER NOT NULL, "
            + "" + COLUM_TIPO_PREGUNTA_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUM_ENCUESTA_ID + ")"
            + "REFERENCES " + NOMBRE_TABLA_ENCUESTA + "(" + COLUM_ID_ENCUESTA_P + "),"
            + "FOREIGN KEY (" + COLUM_TIPO_PREGUNTA_ID + ")"
            + "REFERENCES " + NOMBRE_TABLA_TIPO_PREGUNTA + "(" + COLUM_ID_TABLA_TIPO_PREGUNTA_P + ")"
            + ");";

//    opcion
    public static final String NOMBRE_TABLA_OPCION = "opcion";
    public static final String COLUM_ID_OPCION_P = "id";
    public static final String COLUM_ID_OPCION_L = "idOpcion";
    public static final String COLUM_OPCION = "opcion";
    public static final String COLUM_PREGUNTA_ID = "pregunta_id";

    public static final String TABLA_OPCION = ""
            + "CREATE TABLE " + NOMBRE_TABLA_OPCION + " ("
            + "" + COLUM_ID_OPCION_L + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "" + COLUM_ID_OPCION_P + " INTEGER NOT NULL, "
            + "" + COLUM_OPCION + " TEXT NOT NULL, "
            + "" + COLUM_PREGUNTA_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUM_PREGUNTA_ID + ")"
            + "REFERENCES " + NOMBRE_TABLA_PREGUNTA + "(" + COLUM_ID_PREGUNTA_P + ")"
            + ");";

//    entrevista
    public static final String NOMBRE_TABLA_ENTREVISTA = "entrevista";
    public static final String COLUM_ID_ENTREVISTA_L = "id_entrevista";
    public static final String COLUM_FECHA_REGISTRO = "fecha_registro";
    public static final String COLUM_USUARIO_ID = "usuario_id";

    public static final String TABLA_ENTREVISTA = ""
            + "CREATE TABLE " + NOMBRE_TABLA_ENTREVISTA + " ("
            + "" + COLUM_ID_ENTREVISTA_L + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "" + COLUM_FECHA_REGISTRO + " TEXT NOT NULL, "
            + "" + COLUM_USUARIO_ID + " INTEGER NOT NULL, "
            + "" + COLUM_ENCUESTA_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUM_ENCUESTA_ID + ") "
            + "REFERENCES " + NOMBRE_TABLA_ENCUESTA + "(" + COLUM_ID_ENCUESTA_P + ")"
            + ");";

//    respuesta
    public static final String NOMBRE_TABLA_RESPUESTA = "respuesta";
    public static final String COLUM_ID_RESPUESTA_L = "id_respuesta";
    public static final String COLUM_RESPUESTA = "respuesta";
    public static final String COLUM_ENTREVISTA_ID = "entrevista_id";
    
    public static final String TABLA_RESPUESTA = ""
            + "CREATE TABLE " + NOMBRE_TABLA_RESPUESTA + " ("
            + "" + COLUM_ID_RESPUESTA_L + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "" + COLUM_RESPUESTA + " TEXT NOT NULL, "
            + "" + COLUM_PREGUNTA_ID + " INTEGER NOT NULL, "
            + "" + COLUM_ENTREVISTA_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUM_PREGUNTA_ID + ") "
            + "REFERENCES " + NOMBRE_TABLA_PREGUNTA + "(" + COLUM_ID_PREGUNTA_P + ") "
            + "FOREIGN KEY (" + COLUM_ENTREVISTA_ID + ") "
            + "REFERENCES " + NOMBRE_TABLA_ENTREVISTA + "(" + COLUM_ID_ENTREVISTA_L + ")"
            + ");";
    
    // estado

    public static final String NOMBRE_TABLA_ESTADO= "estado";
    public static final String COLUM_ID_ESTADO_L = "id_estado";
    public static final String COLUM_ID_ESTADO_P = "id";
    public static final String COLUM_NOMBRE_ESTADO = "nombre";
    public static final String COLUM_CODIGO_ESTADO = "codigo";

    public static final String TABLA_ESTADO = ""
            + "CREATE TABLE " + NOMBRE_TABLA_ESTADO + " ("
            + "" + COLUM_ID_ESTADO_L + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "" + COLUM_ID_ESTADO_P + " INTEGER NOT NULL,"
            + "" + COLUM_NOMBRE_ESTADO + " TEXT NOT NULL,"
            + "" + COLUM_CODIGO_ESTADO + " INTEGER NOT NULL"
            + ");";

    //    MUNICIPIO
    public static final String NOMBRE_TABLA_MUNICIPIO = "municipio";
    public static final String COLUM_ID_MUNICIPIO_L = "id_municipio";
    public static final String COLUM_ID_MUNICIPIO_P = "id";
    public static final String COLUM_NOMBRE_MUNICIPIO = "nombre";
    public static final String COLUM_CODIGO_MUNICIPIO = "codigo";
    public static final String COLUM_ESTADO_ID = "estado_id";

    public static final String TABLA_MUNICIPIO = ""
            + "CREATE TABLE " + NOMBRE_TABLA_MUNICIPIO + " ("
            + "" + COLUM_ID_MUNICIPIO_L + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "" + COLUM_ID_MUNICIPIO_P + " INTEGER NOT NULL, "
            + "" + COLUM_NOMBRE_MUNICIPIO + " TEXT NOT NULL, "
            + "" + COLUM_CODIGO_MUNICIPIO + " INTEGER NOT NULL, "
            + "" + COLUM_ESTADO_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUM_ESTADO_ID + ") "
            + "REFERENCES " + NOMBRE_TABLA_ESTADO + "(" + COLUM_ID_ESTADO_P + ")" +
            ");";
}
