package ui.pantallas.empleados;


import domain.ErrorApp;
import domain.modelo.Empleado;
import domain.modelo.Equipo;

import java.util.List;

public record EmpleadosState(List<Empleado> empleadoList, List<Equipo> equipoList, ErrorApp error) {
}
