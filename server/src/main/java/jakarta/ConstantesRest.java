package jakarta;

public class ConstantesRest {
    public static final String ALL_SELECTED = "/allSelected";

    private ConstantesRest() {
    }
    public static final String LOGINROUTE = "/login";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PROYECTOS = "/proyectos";
    public static final String EMPLEADOS = "/empleados";
    public static final String EQUIPO_ID = "/equipo/{id}";
    public static final String ID = "id";
    public static final String PATHID = "/{" + ID + "}";
    public static final String EQUIPOS = "/equipos";
    public static final String API = "/api";

    public static final String FILTER_LOGIN = "FilterLogin";
    public static final String API_EMPLEADOS = "/api/empleados";
    public static final String API_EMPLEADOSALL = "/api/empleados/*";
    public static final String API_PROYECTOS = "/api/proyectos";
    public static final String API_PROYECTOSALL = "/api/proyectos/*";
    public static final String API_EQUIPOS = "/api/equipos";
    public static final String API_EQUIPOSALL = "/api/equipos/*";
    public static final String LOGIN = "LOGIN";
    public static final String NO_AUTORIZADO = "No estás autorizado. Por favor, inicia sesión.";
}
