package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDto implements Serializable {
    private Long id_notificacion;
    private String mensaje;
    private String tipo;
    private LocalDate fecha_envio;
    private Integer leido;
    private Usuario usuario;
}
