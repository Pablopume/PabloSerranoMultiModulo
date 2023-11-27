package ui.pantallas.articles;


import domain.ErrorApp;
import domain.modelo.Empleado;
import domain.modelo.Equipo;
import domain.modelo.Proyecto;

import java.util.List;

public record ArticlesState(List<Empleado> empleadoList, List<Equipo> equipoList, ErrorApp error) {
}
