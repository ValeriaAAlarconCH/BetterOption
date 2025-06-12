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
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column(name = "nombre_categoria", nullable = false)
    private String nombreCategoria;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
