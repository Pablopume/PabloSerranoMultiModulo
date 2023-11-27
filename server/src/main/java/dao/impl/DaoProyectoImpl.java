package dao.impl;

import dao.ConstantesDao;
import dao.DBConnectionPool;
import dao.DaoProyecto;
import dao.Querys;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DaoProyectoImpl implements DaoProyecto {
    private final DBConnectionPool db;

    @Inject
    public DaoProyectoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Proyecto> getAll() {
        List<Proyecto> proyectos = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement();) {

            ResultSet rs = stmt.executeQuery(Querys.SELECT_FROM_PROYECTO);
            proyectos = readRS(rs);
            if (proyectos.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_PROYECTOS);
            }
            db.closeConnection(con);
        } catch (SQLException e) {
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }

        return proyectos;
    }

    @Override
    public Proyecto get(UUID id) {
        Proyecto proyecto = null;
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(Querys.SELECT_FROM_PROYECTO_WHERE_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Proyecto> proyectos = readRS(rs);
            if (proyectos.isEmpty()) {
                throw new NotFoundException(ConstantesDao.NO_HAY_PROYECTO_CON_ESE_ID);
            } else {
                proyecto = proyectos.get(0);
            }
            db.closeConnection(con);
        } catch (SQLException e) {
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return proyecto;
    }

    private List<Proyecto> readRS(ResultSet rs) {
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            while (rs.next()) {
                Proyecto resultProyecto = new Proyecto(
                        UUID.fromString(rs.getString(ConstantesDao.ID)),
                        rs.getString(ConstantesDao.NOMBRE),
                        rs.getString(ConstantesDao.DESCRIPCION),
                        rs.getDate(ConstantesDao.FECHA_INICIO).toLocalDate(),
                        rs.getDate(ConstantesDao.FECHA_FINALIZACION).toLocalDate()
                );
                proyectos.add(resultProyecto);
            }

        } catch (SQLException e) {
            throw new DataBaseCaidaException(ConstantesDao.ERROR_EN_LA_BASE_DE_DATOS);
        }
        return proyectos;
    }
}
