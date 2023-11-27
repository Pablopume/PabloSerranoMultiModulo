package dao.impl;

import dao.DBConnectionPool;
import dao.DaoProyecto;
import dao.exceptions.DataBaseCaidaException;
import dao.exceptions.NotFoundException;
import domain.modelo.Proyecto;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoProyectoImpl implements DaoProyecto {
    private final DBConnectionPool db;

    @Inject
    public DaoProyectoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Proyecto> getAll() {
        List<Proyecto> proyectos = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Proyecto");
            proyectos = readRS(rs);
            if (proyectos.isEmpty()) {
                throw new NotFoundException("No hay proyectos");
            }
            db.closeConnection(con);
        } catch (SQLException e) {
            throw new DataBaseCaidaException("Error en la base de datos");
        }

        return proyectos;
    }

    @Override
    public Proyecto get(String id) {
        Proyecto proyecto = null;
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM Proyecto WHERE ID = ?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            List<Proyecto> proyectos = readRS(rs);
            if (proyectos.isEmpty()) {
                throw new NotFoundException("No hay proyecto con ese id");
            } else {
                proyecto = proyectos.get(0);
            }
            db.closeConnection(con);
        } catch (SQLException e) {
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return proyecto;
    }

    private List<Proyecto> readRS(ResultSet rs) {
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            while (rs.next()) {
                Proyecto resultProyecto = new Proyecto(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFinalizacion").toLocalDate()
                );
                proyectos.add(resultProyecto);
            }

        } catch (SQLException e) {
            throw new DataBaseCaidaException("Error en la base de datos");
        }
        return proyectos;
    }
}
