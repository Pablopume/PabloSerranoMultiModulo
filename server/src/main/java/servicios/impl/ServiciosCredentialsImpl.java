package servicios.impl;

import dao.impl.DaoCredentials;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
        boolean login = false;
        Credentials credentials = daoCredentials.checkCredentials(user);
        if (verifyPassword(password, credentials.getPassword())) {
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
