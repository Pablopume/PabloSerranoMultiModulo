package ui.pantallas;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.common.BasePantallaController;
import ui.common.Pantallas;
import usecases.login.LoginUseCase;

public class LoginController extends BasePantallaController {
    private final LoginUseCase loginUseCase;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    @Inject
    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public void login() {
        if (userField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError("Rellene todos los campos");
        } else {
            loginUseCase.execute(userField.getText(),passwordField.getText()).observeOn(Schedulers.single())
                    .subscribe(either -> Platform.runLater(() -> {
                        if (either.isLeft()) {
                            getPrincipalController().sacarAlertError(either.getLeft().getMensaje());
                        } else {
                            getPrincipalController().cargarPantalla(Pantallas.MENU);
                        }
                    }));
        }
    }

    public void register() {
        getPrincipalController().cargarPantalla(Pantallas.REGISTER);
    }
}

