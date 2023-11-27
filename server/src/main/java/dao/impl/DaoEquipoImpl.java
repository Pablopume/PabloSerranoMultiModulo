package dao.impl;

import dao.ConstantesDao;
import dao.DBConnectionPool;
import dao.DaoEquipo;
import dao.Querys;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
public class DaoEquipoImpl implements DaoEquipo {
    private final DBConnectionPool db;

    @Inject
    public DaoEquipoImpl(DBConnectionPool dbConnectionPool) {
        this.db = dbConnectionPool;
    }

    public List<Equipo> getAll() {
        List<Equipo> equipos = null;
        try (Connection myConnection = db.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery(Querys.SELECT_FROM_EQUIPO);
            equipos = readRS(rs);
            if (equipos.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EQUIPOS);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return equipos;
    }
    public Equipo get(UUID id) {
        Equipo equipo = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement(Querys.SELECT_FROM_EQUIPO_WHERE_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Equipo> equipos = readRS(rs);
            if (equipos.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_EQUIPO_CON_ESE_ID);
            }else {
                equipo = equipos.get(0);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return equipo;
    }

    private List<Equipo> readRS(ResultSet rs) {
        List<Equipo> equipos = new ArrayList<>();
        try {
            while (rs.next()) {
                Equipo resultEquipo = new Equipo(
                        UUID.fromString(rs.getString(ConstantesDao.ID)),
                        rs.getString(ConstantesDao.NOMBRE),
                        rs.getString(ConstantesDao.ESPECIALIZACION));
                equipos.add(resultEquipo);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return equipos;
    }
}
