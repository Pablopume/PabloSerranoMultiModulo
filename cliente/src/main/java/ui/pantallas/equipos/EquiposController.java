package ui.pantallas.equipos;

import dao.common.Constantes;
import domain.modelo.Equipo;
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

public class EquiposController extends BasePantallaController {

    private final EquiposViewModel equiposViewModel;
    @FXML
    private TableColumn<String, Equipo> idColumn;
    @FXML
    private TableColumn<String, Equipo> nameColumn;
    @FXML
    private TableColumn<String, Equipo> especializacionColumn;
    @FXML
    private TableView<Equipo> equiposTable;
    @FXML
    private TextField idSearch;

    @Inject
    public EquiposController(EquiposViewModel equiposViewModel) {
        this.equiposViewModel = equiposViewModel;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        especializacionColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ESPECIALIDAD));
        equiposViewModel.getState().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            if (newValue.error() != null) {
                getPrincipalController().sacarAlertError(newValue.error().getMensaje());
            }
            if (newValue.equipoList() != null) {
                equiposTable.getItems().clear();
                equiposTable.getItems().addAll(newValue.equipoList());
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
        equiposViewModel.getEquipo(UUID.fromString(idSearch.getText()));

    }

    public void reload() {
    equiposViewModel.getAllEquipo();
    }

}
