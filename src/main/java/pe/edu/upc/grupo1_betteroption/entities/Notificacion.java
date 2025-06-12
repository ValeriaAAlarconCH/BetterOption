package pe.edu.upc.grupo1_betteroption.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notificacion;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

