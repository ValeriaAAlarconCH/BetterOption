package pe.edu.upc.grupo1_betteroption.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {
    private Long id_usuario;
    private String nombre;
    private String correo;
    private String password;
    private String rol;
    private LocalDate fechaRegistro;
}
