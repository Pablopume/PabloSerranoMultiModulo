package dao.common;

public class Constantes {
    public static final String EMPLEADOS = "empleados";
    public static final String EMPLEADOS_ID = "empleados/{id}";
    public static final String EMPLEADOS_EQUIPO_ID = "empleados/equipo/{id}";
    public static final String EQUIPOS = "equipos";
    public static final String EQUIPOS_ID = "equipos/{id}";
    public static final String LOGIN = "login";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PROYECTOS = "proyectos";
    public static final String PROYECTOS_ID = "proyectos/{id}";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String SAVING_COOKIES_FAILED_FOR = "Saving cookies failed for ";
    public static final String EMPTYPOINTS = "/...";
    public static final String LOADING_COOKIES_FAILED_FOR = "Loading cookies failed for ";
    public static final String SUFFIX = "\"";
    public static final String COOKIE = "Cookie";
    public static final String COOKIE_2 = "Cookie2";
    public static final String DELIMITERS = ";,";
    public static final char DELIMITER = '=';
    public static final String DOLLAR = "$";
    public static final String EMPTY = "";
    public static final String URLBASEAPI = "http://localhost:8080/server-1.0-SNAPSHOT/api/";
    public static final String ERROR_DESCONOCIDO = "Error desconocido";
    public static final String OK = "OK";
    public static final String APP_TITLE = "app.title";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String ESPECIALIDAD = "especialidad";
    public static final String RELLENA_TODOS_LOS_CAMPOS = "Rellena todos los campos";
    public static final String IOS = "IOS";
    public static final String ANDROID = "Android";
    public static final String UUIDIOS = "7dde4d50-8b03-11ee-b9d1-0242ac120003";
    public static final String UUIDANDROID = "7dde4d50-8b03-11ee-b9d1-0242ac120002";
    public static final String SELECCIONA_UN_EMPLEADO = "Selecciona un empleado";
    public static final String INFORMACION = "Informacion";
    public static final String TEXTOINFO = """
            ADD -> Rellenar los campos y añadir un empleado
            DELETE -> Seleccionar un empleado y pulsar el boton
            UPDATE -> Seleccionar un empleado y rellenar los campos, pulsar el boton
            RELOAD -> Recargar la tabla de todos los usuarios
            ATRAS -> Volver al menu principal
            Filtrar lista por equipo -> Seleccionar un equipo de la tabla de equipos
            Al añadir un empleado si estás filtrando por un equipo solo lo verás en la tabla instantaneamente si pertenece al equipo seleccionado
            Al actualizar, si cambias de equipo al empleado y estás filtrando por otro equipo, desaparecerá de esa tabla ya que ya no pertenece a ese equipo.
            Puedes borrar todos los empleados de un equipo seleccionando el equipo y pulsando el botón de delete.
            Con el botón de delete todos los empleados de la tabla, se borrarán todos los empleados de la tabla de la izquierda.
            """;
    public static final String SELECCIONA_UN_EQUIPO = "Selecciona un equipo";
    public static final String DESCRIPCION = "descripcion";
    public static final String EMAIL_NO_VALIDO = "Email no válido";
    public static final String FECHA_DE_NACIMIENTO_NO_VALIDA = "Fecha de nacimiento no válida";
    public static final String A_Z_A_Z = ".*[a-zA-Z]+.*";
    public static final String TELEFONO_NO_VALIDO = "Teléfono no válido";
    public static final String EMAILPATRON = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String ALL_SELECTED = "empleados/allSelected";
    public static final String TABLAVACIA = "La tablla de la izquierda está vacía";

    private Constantes() {
    }
    public static final String ID = "id";
    public static final String I_18_N_TEXTOS = "/i18n/textos";
    public static final String FXML_PRINCIPAL_FXML = "/fxml/principal.fxml";
    public static final String QUIT_APPLICATION = "Quit application";
    public static final String CLOSE_WITHOUT_SAVING = "Close without saving?";
}
