package pe.edu.upc.grupo1_betteroption.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Microempresa")
public class Microempresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_microempresa", nullable = false)
    private Long id_microempresa;

    @Column(name = "nombre_negocio", nullable = false, length = 100)
    private String nombreNegocio;

    @Column(name = "rubro", nullable = false, length = 100)
    private String rubro;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
}
