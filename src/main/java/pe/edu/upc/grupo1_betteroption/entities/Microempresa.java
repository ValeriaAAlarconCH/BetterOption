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

    @Column(name = "nombre_negocio", nullable = false)
    private String nombreNegocio;

    @Column(name = "rubro", nullable = false)
    private String rubro;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
