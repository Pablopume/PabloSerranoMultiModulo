package ui.pantallas.equipos;

import domain.ErrorApp;
import domain.modelo.Equipo;

import java.util.List;

public record EquiposState(List<Equipo> equipoList, ErrorApp error) {
}
