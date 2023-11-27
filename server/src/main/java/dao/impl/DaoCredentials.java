package dao.impl;

import dao.DBConnectionPool;
import dao.HasheoContrasenyas;
import dao.exceptions.DataBaseCaidaException;
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

    public Credentials checkCredentials(String user) {
        Credentials credentials = new Credentials();
        try (Connection con = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Credentials WHERE username = ?")) {
            preparedStatement.setString(1, user);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                credentials.setUsername(rs.getString("username"));
                credentials.setPassword(rs.getString("password"));
             //   String storedHash = rs.getString("password");
               // return verifyPassword(password, storedHash);
            }

        } catch (SQLException ex) {
            throw new DataBaseCaidaException("Error al comprobar las credenciales");
        }
        return credentials;
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
