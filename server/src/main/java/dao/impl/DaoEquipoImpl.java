package dao.impl;

import dao.DBConnectionPool;
import dao.DaoEquipo;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Equipo;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            ResultSet rs = statement.executeQuery("SELECT * FROM Equipo");
            equipos = readRS(rs);
            if (equipos.isEmpty()) {
                throw new NotFoundException("No hay equipos");
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return equipos;
    }
    public Equipo get(String id) {
        Equipo equipo = null;
        try (Connection myConnection = db.getConnection();
             PreparedStatement statement = myConnection.prepareStatement("SELECT * FROM Equipo WHERE ID = ?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            List<Equipo> equipos = readRS(rs);
            if (equipos.isEmpty()) {
                throw new NotFoundException("No hay equipo con ese id");
            }else {
                equipo = equipos.get(0);
            }
            db.closeConnection(myConnection);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return equipo;
    }

    private List<Equipo> readRS(ResultSet rs) {
        List<Equipo> equipos = new ArrayList<>();
        try {
            while (rs.next()) {
                Equipo resultEquipo = new Equipo(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("especializacion"));
                equipos.add(resultEquipo);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return equipos;
    }
}
