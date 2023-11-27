package ui.common;

import lombok.Getter;
import ui.principal.PrincipalController;

import java.io.IOException;
@Getter
public class BasePantallaController {
    private PrincipalController principalController;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }


    public void principalCargado() throws IOException {
        // No hace nada por defecto
    }
}
