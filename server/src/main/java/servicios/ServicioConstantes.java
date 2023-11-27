package servicios;

public class ServicioConstantes {
    private ServicioConstantes() {
    }

    public static final String LOGIN = "LOGIN";
    public static final String EMAIL_NO_VALIDO = "Email no válido";
    public static final String FECHA_DE_NACIMIENTO_NO_VALIDA = "Fecha de nacimiento no válida";
    public static final String TELEFONO_NO_VALIDO = "Teléfono no válido";
    public static final String PATRON = ".*[a-zA-Z]+.*";
    public static final String PATRONMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
}
