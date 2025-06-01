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
public class CatalogoPromociones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_catalogopromociones;
    private String nombreCatalogo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @ManyToOne
    @JoinColumn(name = "id_microempresa")
    private Microempresa microempresa;
}
