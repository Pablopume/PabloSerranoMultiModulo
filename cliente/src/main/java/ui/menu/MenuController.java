package ui.menu;

import javafx.fxml.FXML;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {


    @FXML
    private void enterEmpleados() {
        getPrincipalController().cargarPantalla(Pantallas.EMPLEADOS);
    }

    @FXML
    private void enterEquipos() {
        getPrincipalController().cargarPantalla(Pantallas.EQUIPOS);
    }

    public void enterProyectos() {
        getPrincipalController().cargarPantalla(Pantallas.PROYECTOS);
    }
}
