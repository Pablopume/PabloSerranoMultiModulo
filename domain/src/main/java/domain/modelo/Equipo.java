package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class Equipo {
private UUID id;
private String nombre;
private String especialidad;
}
