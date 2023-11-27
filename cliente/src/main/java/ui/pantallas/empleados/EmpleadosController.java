package ui.pantallas.empleados;


import dao.common.Constantes;
import domain.modelo.Empleado;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmpleadosController extends BasePantallaController {
    @FXML
    private TableView<Equipo> equiposTable;
    @FXML
    private TableColumn<String, Equipo> especializacionEquipo;
    @FXML
    private TableColumn<String, Equipo> nameEquipo;
    @FXML
    private TableColumn<String, Equipo> idEquipo;
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
    private TableView<Empleado> empleadosTable;
    @FXML
    private TableColumn<String, Empleado> idColumn;
    @FXML
    private TableColumn<String, Empleado> nameColumn;
    @FXML
    private TableColumn<String, Empleado> apellidoColumn;
    @FXML
    private TextField nameField;


    private final EmpleadosViewModel empleadosViewModel;

    @Inject
    EmpleadosController(EmpleadosViewModel empleadosViewModel) {
        this.empleadosViewModel = empleadosViewModel;
    }

    public void initialize() {
        datePicker.setEditable(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.APELLIDO));
        idEquipo.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nameEquipo.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        especializacionEquipo.setCellValueFactory(new PropertyValueFactory<>(Constantes.ESPECIALIDAD));
        empleadosViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            if (stateNew.error() != null) {
                getPrincipalController().sacarAlertError(stateNew.error().getMensaje());
            }

            if (stateNew.empleadoList() != null) {
                empleadosTable.getItems().clear();
                empleadosTable.getItems().addAll(stateNew.empleadoList());

            }
            if (stateNew.equipoList() != null) {
                equiposTable.getItems().clear();
                equiposTable.getItems().addAll(stateNew.equipoList());
            }
        }));
        equiposTable.setOnMouseClicked(event -> {
            Equipo selectedEquipo = equiposTable.getSelectionModel().getSelectedItem();
            empleadosViewModel.getAllByEquipo(selectedEquipo.getId());


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
    private void addEmpleado() {
        if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || datePicker.getValue() == null || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || comboTipo.getValue() == null) {
            getPrincipalController().sacarAlertError(Constantes.RELLENA_TODOS_LOS_CAMPOS);

        } else {
            UUID idTeam = null;
            if (comboTipo.getValue().equals(Constantes.IOS)) {
                idTeam = UUID.fromString(Constantes.UUIDIOS);
            }
            if (comboTipo.getValue().equals(Constantes.ANDROID)) {
                idTeam = UUID.fromString(Constantes.UUIDANDROID);
            }
            empleadosViewModel.addEmpleado(new Empleado(nameField.getText(), surnameField.getText(), datePicker.getValue(), emailField.getText(), phoneField.getText(), idTeam));

        }
    }

    @FXML
    private void reload() {
        empleadosViewModel.getAllEmpleados();
        empleadosViewModel.getAllEquipo();

    }


    public void delete() {
        Empleado selectedEmpleado = empleadosTable.getSelectionModel().getSelectedItem();
        if (selectedEmpleado == null) {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_EMPLEADO);

        } else {
            empleadosViewModel.deleteEmpleado((selectedEmpleado.getId()));
        }
    }

    public void update() {


        Empleado selectedEmpleado = empleadosTable.getSelectionModel().getSelectedItem();
        if (selectedEmpleado == null) {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_EMPLEADO);

        } else if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || datePicker.getValue() == null || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.RELLENA_TODOS_LOS_CAMPOS);

        } else {
            UUID idTeam = null;
            if (comboTipo.getValue().equals(Constantes.IOS)) {
                idTeam = UUID.fromString(Constantes.UUIDIOS);
            }
            if (comboTipo.getValue().equals(Constantes.ANDROID)) {
                idTeam = UUID.fromString(Constantes.UUIDANDROID);
            }
            Empleado empleado = new Empleado(selectedEmpleado.getId(), nameField.getText(), surnameField.getText(), datePicker.getValue(), emailField.getText(), phoneField.getText(), idTeam);
            empleadosViewModel.updateEmpleado(empleado);
        }

    }

    public void info() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constantes.INFORMACION);
        alert.setHeaderText(Constantes.INFORMACION);
        alert.setContentText(Constantes.TEXTOINFO);

        alert.showAndWait();
    }

    public void deleteEquipos() {
        Equipo selectedEquipo = equiposTable.getSelectionModel().getSelectedItem();
        if (selectedEquipo == null) {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_EQUIPO);
        } else {
            empleadosViewModel.deleteEmpleadoByEquipo(selectedEquipo.getId());
        }
    }

    public void deleteEmpleadosPorUUID() {
        List<UUID> listaId = new ArrayList<>();
        for (Empleado empleado : empleadosTable.getItems()) {
            listaId.add(empleado.getId());
        }
        if (listaId.isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.TABLAVACIA);
        } else {
            empleadosViewModel.deleteListaEmpleados(listaId);
        }
    }
}
