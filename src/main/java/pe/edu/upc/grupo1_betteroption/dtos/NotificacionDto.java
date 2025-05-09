package pe.edu.upc.grupo1_betteroption.dtos;

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
public class NotificacionDto implements Serializable {
    private Long id_notificacion;
    private String mensaje;
    private String tipo;
    private LocalDate fechaEnvio;
    private Integer leido;
}
