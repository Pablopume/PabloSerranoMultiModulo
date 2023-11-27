package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Empleado {
    UUID id;
    String nombre;
    String apellido;
    LocalDate fechaNacimiento;
    String email;
    String telefono;
    UUID equipoId;

    public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, String email,
                    String telefono, UUID equipoId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.equipoId = equipoId;
    }
}
