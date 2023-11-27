package ui.common;

public enum Pantallas {
    REGISTER("/fxml/register.fxml"),
    LOGIN("/fxml/login.fxml"),
    MENU("/fxml/menu.fxml"),
    NEWSPAPERS("/fxml/newspapers.fxml"),
    ARTICLES("/fxml/empleados.fxml"),
    READERS("/fxml/readers.fxml");
    private final String ruta;
    Pantallas(String ruta){this.ruta=ruta;}
    public String getRuta(){return ruta;}
}
