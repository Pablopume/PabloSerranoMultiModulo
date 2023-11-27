package dao.impl;

import dao.ConstantesDao;
import dao.DBConnectionPool;
import dao.DaoEmpleado;
import dao.Querys;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Empleado;
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
            ResultSet rs = statement.executeQuery(Querys.SELECT_FROM_EMPLEADO);
            empleados = readRS(rs);
            if (empleados.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADOS);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return empleados;
    }

    @Override
    public void delete(UUID equipoId) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.DELETE_FROM_EMPLEADO_WHERE_IDEQUIPO)) {
            statement.setString(1, String.valueOf(equipoId));
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADOS_EN_ESE_EQUIPO);
            }

            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Empleado> getAll(UUID equipoId) {
        List<Empleado> empleados;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.SELECT_FROM_EMPLEADO_WHERE_IDEQUIPO)) {
            statement.setString(1, String.valueOf(equipoId));
            ResultSet rs = statement.executeQuery();
            empleados = readRS(rs);
            if (empleados.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADOS);
            }

            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return empleados;
    }

    private List<Empleado> readRS(ResultSet rs) {
        List<Empleado> empleados = new ArrayList<>();
        try {
            while (rs.next()) {
                Empleado resultEmpleado = new Empleado(
                        UUID.fromString(rs.getString(ConstantesDao.ID)),
                        rs.getString(ConstantesDao.NOMBRE),
                        rs.getString(ConstantesDao.APELLIDO),
                        rs.getDate(ConstantesDao.DOB).toLocalDate(),
                        rs.getString(ConstantesDao.EMAIL),
                        rs.getString(ConstantesDao.TELEFONO),
                        UUID.fromString(rs.getString(ConstantesDao.ID_EQUIPO)));
                empleados.add(resultEmpleado);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return empleados;
    }


    @Override
    public Empleado get(UUID id) {
        Empleado empleado = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.SELECT_FROM_EMPLEADO_WHERE_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Empleado> empleados = readRS(rs);
            if (empleados.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADO_CON_ESE_ID);
            } else {
                empleado = empleados.get(0);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return empleado;
    }

    @Override
    public Empleado add(Empleado empleado) {
        empleado.setId(UUID.randomUUID());
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.INSERT_INTO_EMPLEADO_VALUES)) {
            statement.setString(1, String.valueOf(empleado.getId()));
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getApellido());
            statement.setDate(4, Date.valueOf(empleado.getFechaNacimiento()));
            statement.setString(5, empleado.getEmail());
            statement.setString(6, empleado.getTelefono());
            statement.setString(7, String.valueOf(empleado.getEquipoId()));
            statement.executeUpdate();
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return empleado;
    }

    @Override
    public Empleado update(Empleado empleado) {
        Empleado resultEmpleado = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.UPDATE_EMPLEADO_SET_NOMBRE_APELLIDO_DOB_EMAIL_TELEFONO_IDEQUIPO_WHERE_ID)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setDate(3, Date.valueOf(empleado.getFechaNacimiento()));
            statement.setString(4, empleado.getEmail());
            statement.setString(5, empleado.getTelefono());
            statement.setString(6, String.valueOf(empleado.getEquipoId()));
            statement.setString(7, String.valueOf(empleado.getId()));
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADO_CON_ESE_ID);
            }
            resultEmpleado = empleado;
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return resultEmpleado;
    }

    @Override
    public void delete(Empleado empleado) {
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.DELETE_FROM_EMPLEADO_WHERE_ID)) {
            statement.setString(1, String.valueOf(empleado.getId()));
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EMPLEADO_CON_ESE_ID);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
