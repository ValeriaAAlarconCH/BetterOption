package pe.edu.upc.grupo1_betteroption.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Microempresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_microempresa;
    private String nombreNegocio;
    private String rubro;
    private String direccion;
    private Integer telefono;
    private String email;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
