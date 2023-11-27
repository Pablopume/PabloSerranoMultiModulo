package ui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {


    @FXML
    private void enterNewspapers() {
        getPrincipalController().cargarPantalla(Pantallas.ARTICLES);
    }

    @FXML
    private void enterArticles() {
        getPrincipalController().cargarPantalla(Pantallas.NEWSPAPERS);
    }

    public void enterReaders() {
        getPrincipalController().cargarPantalla(Pantallas.READERS);
    }
}
