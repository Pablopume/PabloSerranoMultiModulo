package servicios.impl;

import dao.impl.DaoCredentials;
import domain.modelo.Credentials;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

public class ServiciosCredentialsImpl {
   private final DaoCredentials daoCredentials;

    @Inject
    public ServiciosCredentialsImpl(DaoCredentials daoCredentials, HttpServletRequest request) {
        this.daoCredentials = daoCredentials;
    }

    public boolean checkCredentials(String user, String password) {
        return daoCredentials.checkCredentials(user, password);
    }
    public Credentials addCredentials(Credentials credentials) {
        return daoCredentials.addCredentials(credentials);
    }
}
