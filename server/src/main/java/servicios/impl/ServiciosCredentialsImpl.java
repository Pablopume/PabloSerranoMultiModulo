package servicios.impl;

import dao.impl.DaoCredentials;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import domain.modelo.Credentials;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;

public class ServiciosCredentialsImpl {
    private final DaoCredentials daoCredentials;
    @Context
    private HttpServletRequest request;

    @Inject
    public ServiciosCredentialsImpl(DaoCredentials daoCredentials, HttpServletRequest request) {
        this.daoCredentials = daoCredentials;
    }

    public boolean checkCredentials(String user, String password) {
        boolean login = false;
        request.getSession().setAttribute("LOGIN", null);
        Credentials credentials = daoCredentials.checkCredentials(user);
        if (verifyPassword(password, credentials.getPassword())) {
            request.getSession().setAttribute("LOGIN", true);
            login = true;
        }
        return login;
    }

    public boolean verifyPassword(String providedPassword, String storedHash) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.verify(storedHash, providedPassword.toCharArray());
        } finally {
            argon2.wipeArray(providedPassword.toCharArray());
        }
    }

    public Credentials addCredentials(Credentials credentials) {
        return daoCredentials.addCredentials(credentials);
    }
}
