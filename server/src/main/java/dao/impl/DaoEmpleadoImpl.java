package dao.impl;

import dao.DBConnectionPool;
import dao.DaoEmpleado;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Empleado;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
public class DaoEmpleadoImpl implements DaoEmpleado {
    private final DBConnectionPool db;

    @Inject
    public DaoEmpleadoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();
        try (Connection myConnection = db.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Empleado");
            empleados = readRS(rs);
            if (empleados.isEmpty()) {
                throw new NotFoundException("No hay empleados");
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return empleados;
    }
    @Override
    public void delete(String equipoId) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("DELETE FROM Empleado WHERE IDEQUIPO = ?")) {
            statement.setString(1, equipoId);
            int rs=statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException("No hay empleados en ese equipo");
            }

            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
    @Override
    public List<Empleado> getAll(String equipoId) {
        List<Empleado> empleados;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("SELECT * FROM Empleado WHERE IDEQUIPO = ?")) {
            statement.setString(1, equipoId);
            ResultSet rs = statement.executeQuery();
            empleados = readRS(rs);
            if (empleados.isEmpty()) {
                throw new NotFoundException("No hay empleados");
            }

            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return empleados;
    }

    private List<Empleado> readRS(ResultSet rs) {
        List<Empleado> empleados = new ArrayList<>();
        try {
            while (rs.next()) {
                Empleado resultEmpleado = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("idEquipo"));
                empleados.add(resultEmpleado);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return empleados;
    }


    @Override
    public Empleado get(String id) {
        Empleado empleado = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("SELECT * FROM Empleado WHERE ID = ?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
                List<Empleado> empleados = readRS(rs);
                if (empleados.isEmpty()) {
                    throw new NotFoundException("No hay empleado con ese id");
                }else {
                    empleado = empleados.get(0);
                }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return empleado;
    }

    @Override
    public Empleado add(Empleado empleado) {
        empleado.setId(UUID.randomUUID().toString());
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("INSERT INTO Empleado VALUES (?,?,?,?,?,?,?)")) {
            statement.setString(1, empleado.getId());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getApellido());
            statement.setDate(4, Date.valueOf(empleado.getFechaNacimiento()));
            statement.setString(5, empleado.getEmail());
            statement.setString(6, empleado.getTelefono());
            statement.setString(7, empleado.getEquipoId());
            statement.executeUpdate();
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return empleado;
    }

    @Override
    public Empleado update(Empleado empleado) {
        Empleado resultEmpleado = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("UPDATE Empleado SET NOMBRE = ?, APELLIDO = ?, DOB = ? WHERE ID = ?")) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setDate(3, Date.valueOf(empleado.getFechaNacimiento()));
            statement.setString(4, empleado.getId());
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException("No hay empleado con ese id");
            }
            resultEmpleado = empleado;
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return resultEmpleado;
    }

    @Override
    public void delete(Empleado empleado) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("DELETE FROM Empleado WHERE ID = ?")) {
            statement.setString(1, empleado.getId());
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException("No hay empleado con ese id");
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
