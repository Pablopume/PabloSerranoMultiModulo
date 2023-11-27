package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Empleado {
    String id;
    String nombre;
    String apellido;
    LocalDate fechaNacimiento;
    String email;
    String telefono;
    String equipoId;

    public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, String email,
                    String telefono, String equipoId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.equipoId = equipoId;
    }
}
