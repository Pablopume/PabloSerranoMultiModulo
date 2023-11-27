package ui.pantallas.articles;


import domain.modelo.Empleado;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class ArticlesController extends BasePantallaController {
    public ComboBox<String> comboTipo1;
    public ComboBox<String> comboNewspaper;
    public TableView<Equipo> equiposTable;
    public TableColumn<String, Equipo> especializacionEquipo;
    public TableColumn<String, Equipo> nameEquipo;
    public TableColumn<String, Equipo> idEquipo;
    public Button deleteButton;
    public Button updateButton;
    public Button infoButton;
    public Button deleteByEquipoButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private TableView<Empleado> articlesTable;
    @FXML
    private TableColumn<String, Empleado> idColumn;
    @FXML
    private TableColumn<String, Empleado> nameColumn;
    @FXML
    private TableColumn<String, Empleado> apellidoColumn;
    @FXML
    private TextField nameField;


    private final ArticlesViewModel articlesViewModel;

    @Inject
    ArticlesController(ArticlesViewModel articlesViewModel) {
        this.articlesViewModel = articlesViewModel;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        idEquipo.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameEquipo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        especializacionEquipo.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        articlesViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            if (stateNew.error() != null) {
                getPrincipalController().sacarAlertError(stateNew.error().getMensaje());
            }

            if (stateNew.empleadoList() != null) {
                articlesTable.getItems().clear();
                articlesTable.getItems().addAll(stateNew.empleadoList());

            }
            if (stateNew.equipoList() != null) {
                equiposTable.getItems().clear();
                equiposTable.getItems().addAll(stateNew.equipoList());
            }
        }));
        equiposTable.setOnMouseClicked(event -> {
            Equipo selectedEquipo = equiposTable.getSelectionModel().getSelectedItem();
            articlesViewModel.getAllByEquipo(selectedEquipo.getId());
            System.out.println(selectedEquipo.getId());
        });

    }


    @Override
    public void principalCargado() {
        reload();
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }


    @FXML
    private void addArticle() {
        if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || datePicker.getValue() == null || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || comboTipo.getValue() == null) {
            getPrincipalController().sacarAlertError("Rellena todos los campos");

        }else {
            String idEquipo = null;
            if (comboTipo.getValue().equals("IOS")) {
                idEquipo = "7dde4d50-8b03-11ee-b9d1-0242ac120003";
            }
            if (comboTipo.getValue().equals("Android")) {
                idEquipo = "7dde4d50-8b03-11ee-b9d1-0242ac120002";
            }
            articlesViewModel.addEmpleado(new Empleado(nameField.getText(), surnameField.getText(), datePicker.getValue(), emailField.getText(), phoneField.getText(), idEquipo));
        }
    }

    @FXML
    private void reload() {
        articlesViewModel.getAllEmpleados();
        articlesViewModel.getAllEquipo();
    }


    public void delete() {
        Empleado selectedEmpleado = articlesTable.getSelectionModel().getSelectedItem();
        if (selectedEmpleado == null) {
            getPrincipalController().sacarAlertError("Selecciona un empleado");

        } else {
            articlesViewModel.deleteEmpleado(selectedEmpleado.getId());
        }
    }

    public void update() {
        Empleado selectedEmpleado = articlesTable.getSelectionModel().getSelectedItem();
        if (selectedEmpleado == null) {
            getPrincipalController().sacarAlertError("Selecciona un empleado");

        } else if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || datePicker.getValue() == null || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError("Rellena todos los campos");

        }else {
            Empleado empleado = new Empleado(selectedEmpleado.getId(), nameField.getText(), surnameField.getText(), datePicker.getValue(), emailField.getText(), phoneField.getText(), selectedEmpleado.getEquipoId());
            articlesViewModel.updateEmpleado(empleado);
        }

    }

    public void info() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("""
    ADD -> Rellenar los campos y añadir un empleado
    DELETE -> Seleccionar un empleado y pulsar el boton
    UPDATE -> Seleccionar un empleado y rellenar los campos, pulsar el boton
    RELOAD -> Recargar la tabla de todos los usuarios
    ATRAS -> Volver al menu principal
    Filtrar lista por equipo -> Seleccionar un equipo de la tabla de equipos
    """);

        alert.showAndWait();
    }

    public void deleteEquipos() {
        Equipo selectedEquipo = equiposTable.getSelectionModel().getSelectedItem();
        if (selectedEquipo == null) {
            getPrincipalController().sacarAlertError("Selecciona un equipo");

        } else {
            articlesViewModel.deleteEmpleadoByEquipo(selectedEquipo.getId());
        }
    }
}
