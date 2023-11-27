package ui.pantallas.readers;

import domain.modelo.Equipo;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class ReadersController extends BasePantallaController {

    private final ReadersViewModel readersViewModel;
    public TableColumn<String, Proyecto> idColumn;
    public TableColumn<String, Proyecto> nameColumn;
    public TableColumn<String, Proyecto> descripcionColumn;
    public TableView<Proyecto> newspapersTable;
    public TextField idSearch;
    @Inject
    public ReadersController(ReadersViewModel readersViewModel) {
        this.readersViewModel = readersViewModel;
    }


    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        readersViewModel.getState().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
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
        readersViewModel.getEquipo(idSearch.getText());

    }

    public void reload() {
        readersViewModel.getAllEquipo();
    }

}

