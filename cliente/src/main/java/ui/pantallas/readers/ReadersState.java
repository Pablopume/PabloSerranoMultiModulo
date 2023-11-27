package ui.pantallas.readers;

import domain.ErrorApp;
import domain.modelo.Equipo;
import domain.modelo.Proyecto;

import java.util.List;

public record ReadersState(List<Proyecto> equipoList, ErrorApp error) {
}
