package ui.common;

public enum Pantallas {
    REGISTER(ConstantesPantallas.FXML_REGISTER_FXML),
    LOGIN(ConstantesPantallas.FXML_LOGIN_FXML),
    MENU(ConstantesPantallas.FXML_MENU_FXML),
    EQUIPOS(ConstantesPantallas.FXML_EQUIPOS_FXML),
    EMPLEADOS(ConstantesPantallas.FXML_EMPLEADOS_FXML),
    PROYECTOS(ConstantesPantallas.FXML_PROYECTOS_FXML);
    private final String ruta;
    Pantallas(String ruta){this.ruta=ruta;}
    public String getRuta(){return ruta;}


}
