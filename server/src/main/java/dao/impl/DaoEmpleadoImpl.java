package dao.impl;

import dao.ConstantesDao;
import dao.DBConnectionPool;
import dao.DaoEmpleado;
import domain.modelo.Empleado;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DaoEmpleadoImpl implements DaoEmpleado {
    private final DBConnectionPool db;

    @Inject
    public DaoEmpleadoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Empleado> getAll() {
        try (Connection myConnection = db.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery("SELECT * FROM EMPLEADOS");
            List<Empleado> empleados= readRS(rs);

            db.closeConnection(myConnection);
        } catch (SQLException e) {
            result = Either.left(ConstantesDao.ERROR_WHILE_RETRIEVING_CUSTOMERS);

        }
        return result;
    }

    private List<Empleado> readRS(ResultSet rs) {
try{
    List<Empleado> empleados = new ArrayList<>();
    while (rs.next()) {
        Empleado resultEmpleado = new Empleado(
                rs.getInt(Constantes.ID1),
                rs.getString(ConstantesDao.FIRST_NAME),
                rs.getString(ConstantesDao.LAST_NAME),
                rs.getString(ConstantesDao.EMAIL),
                rs.getString(ConstantesDao.PHONE),
                rs.getDate(ConstantesDao.DATE_OF_BIRTH).toLocalDate()
    }
    return empleados;
}
    }

    @Override
    public Empleado get(int id) {
        return null;
    }

    @Override
    public Empleado add(Empleado empleado) {
        return null;
    }

    @Override
    public Empleado update(Empleado empleado) {
        return null;
    }

    @Override
    public void delete(Empleado empleado) {

    }
}
