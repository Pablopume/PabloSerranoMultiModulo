package dao;

public class ConstantesDao {
    private ConstantesDao() {
    }

    public static final String USERNAME = "username";
    public static final String ERROR_AL_COMPROBAR_LAS_CREDENCIALES = "Error al comprobar las credenciales";
    public static final String SELECT_FROM_CREDENTIALS_WHERE_USERNAME = "SELECT * FROM Credentials WHERE username = ?";
    public static final String INSERT_INTO_CREDENTIALS_USERNAME_PASSWORD_VALUES = "INSERT INTO Credentials (username, password) VALUES (?, ?)";
    public static final String NO_HAY_EMPLEADOS = "No hay empleados";
    public static final String NO_HAY_EMPLEADOS_EN_ESE_EQUIPO = "No hay empleados en ese equipo";
    public static final String ERROR_EN_LA_BASE_DE_DATOS = "Error en la base de datos";
    public static final String NO_HAY_EMPLEADO_CON_ESE_ID = "No hay empleado con ese id";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String DOB = "dob";
    public static final String EMAIL = "email";
    public static final String TELEFONO = "telefono";
    public static final String ID_EQUIPO = "idEquipo";
    public static final String NO_HAY_EQUIPOS = "No hay equipos";
    public static final String NO_HAY_EQUIPO_CON_ESE_ID = "No hay equipo con ese id";
    public static final String ESPECIALIZACION = "especializacion";
    public static final String NO_HAY_PROYECTOS = "No hay proyectos";
    public static final String NO_HAY_PROYECTO_CON_ESE_ID = "No hay proyecto con ese id";
    public static final String DESCRIPCION = "descripcion";
    public static final String FECHA_INICIO = "fechaInicio";
    public static final String FECHA_FINALIZACION = "fechaFinalizacion";


    public static final String URL_DB = "urlDB";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String DRIVER = "driver";
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";

}
