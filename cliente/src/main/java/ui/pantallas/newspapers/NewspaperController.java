package ui.pantallas.newspapers;

import domain.modelo.Equipo;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class NewspaperController extends BasePantallaController {
    private final NewspaperViewModel newspaperViewModel;
    public TableColumn<String, Equipo> idColumn;
    public TableColumn<String, Equipo> nameColumn;
    public TableColumn<String, Equipo> especializacionColumn;
    public TableView<Equipo> newspapersTable;
    public TextField idSearch;

    @Inject
    public NewspaperController(NewspaperViewModel newspaperViewModel) {
        this.newspaperViewModel = newspaperViewModel;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        especializacionColumn.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        newspaperViewModel.getState().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            if (newValue.error() != null) {
                getPrincipalController().sacarAlertError(newValue.error().getMensaje());
            }
            if (newValue.equipoList() != null) {
                newspapersTable.getItems().clear();
                newspapersTable.getItems().addAll(newValue.equipoList());
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
        newspaperViewModel.getEquipo(idSearch.getText());

    }

    public void reload() {
    newspaperViewModel.getAllEquipo();
    }

}
