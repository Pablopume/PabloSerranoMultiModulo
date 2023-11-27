package ui.pantallas.proyectos;

import domain.ErrorApp;
import domain.modelo.Proyecto;

import java.util.List;

public record ProyectosState(List<Proyecto> equipoList, ErrorApp error) {
}
