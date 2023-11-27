package ui.pantallas.newspapers;

import domain.ErrorApp;
import domain.modelo.Equipo;

import java.util.List;

public record NewspaperState(List<Equipo> equipoList, ErrorApp error) {
}
