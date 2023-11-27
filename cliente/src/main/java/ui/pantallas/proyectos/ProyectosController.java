package ui.pantallas.proyectos;

import dao.common.Constantes;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.util.UUID;

public class ProyectosController extends BasePantallaController {

    private final ProyectosViewModel proyectosViewModel;
    @FXML
    private TableColumn<String, Proyecto> idColumn;
    @FXML
    private TableColumn<String, Proyecto> nameColumn;
    @FXML
    private TableColumn<String, Proyecto> descripcionColumn;
    @FXML
    private TableView<Proyecto> proyectosTable;
    @FXML
    private TextField idSearch;
    @Inject
    private ProyectosController(ProyectosViewModel proyectosViewModel) {
        this.proyectosViewModel = proyectosViewModel;
    }


    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.DESCRIPCION));
        proyectosViewModel.getState().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            if (newValue.error() != null) {
                getPrincipalController().sacarAlertError(newValue.error().getMensaje());
            }
            if (newValue.equipoList() != null) {
                proyectosTable.getItems().clear();
                proyectosTable.getItems().addAll(newValue.equipoList());
            }
        }));
    }

    @Override
    public void principalCargado() {
        reload();
    }
    public void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    public void buscar() {
        proyectosViewModel.getEquipo(UUID.fromString(idSearch.getText()));

    }

    public void reload() {
        proyectosViewModel.getAllEquipo();
    }

}

