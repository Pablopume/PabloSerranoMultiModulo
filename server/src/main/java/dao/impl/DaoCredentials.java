package dao.impl;

import dao.ConstantesDao;
import dao.DBConnectionPool;
import dao.HasheoContrasenyas;
import dao.exceptions.DataBaseCaidaException;
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
             PreparedStatement preparedStatement = con.prepareStatement(ConstantesDao.SELECT_FROM_CREDENTIALS_WHERE_USERNAME)) {
            preparedStatement.setString(1, user);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                credentials.setUsername(rs.getString(ConstantesDao.USERNAME));
                credentials.setPassword(rs.getString(ConstantesDao.PASSWORD));

            }

        } catch (SQLException ex) {
            throw new DataBaseCaidaException(ConstantesDao.ERROR_AL_COMPROBAR_LAS_CREDENCIALES);
        }
        return credentials;
    }


    public Credentials addCredentials(Credentials credentials) {
        try (Connection con = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantesDao.INSERT_INTO_CREDENTIALS_USERNAME_PASSWORD_VALUES)) {
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
