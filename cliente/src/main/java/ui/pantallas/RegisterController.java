package ui.pantallas;

import dao.common.Constantes;
import domain.modelo.Credentials;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.common.BasePantallaController;
import ui.common.Pantallas;
import usecases.login.AddCredentialsUseCase;

public class RegisterController extends BasePantallaController {
    private final AddCredentialsUseCase addCredentialsUseCase;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @Inject
    public RegisterController(AddCredentialsUseCase addCredentialsUseCase) {
        this.addCredentialsUseCase = addCredentialsUseCase;
    }


    public void register() {
        if (userField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.RELLENA_TODOS_LOS_CAMPOS);
        } else {
            addCredentialsUseCase.execute(new Credentials(userField.getText(),passwordField.getText())).observeOn(Schedulers.single())
                    .subscribe(either -> Platform.runLater(() -> {
                        if (either.isLeft()) {
                            getPrincipalController().sacarAlertError(either.getLeft().getMensaje());
                        } else {
                            getPrincipalController().cargarPantalla(Pantallas.LOGIN);
                        }
                    }));
        }
    }
}
