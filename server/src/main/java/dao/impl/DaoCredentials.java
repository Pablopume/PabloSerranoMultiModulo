package dao.impl;

import dao.DBConnectionPool;
import dao.HasheoContrasenyas;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import domain.modelo.Credentials;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoCredentials {
   private final DBConnectionPool dbConnectionPool;
    @Inject
    public DaoCredentials(DBConnectionPool dbConnectionPool) {
        this.dbConnectionPool = dbConnectionPool;
    }

    public boolean checkCredentials(String user, String password) {
        try (Connection con = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Credentials WHERE username = ?")) {
            preparedStatement.setString(1, user);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                return verifyPassword(password, storedHash);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
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
        try (Connection con = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Credentials (username, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, credentials.getUsername());
            HasheoContrasenyas hasheoContrasenyas = new HasheoContrasenyas();
            preparedStatement.setString(2, hasheoContrasenyas.hashPassword(credentials.getPassword()));
            preparedStatement.executeUpdate();
            return credentials;
        } catch (SQLException ex) {
            return credentials;
        }
    }


}
