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
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Producto;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagen;
    @ManyToOne
    @JoinColumn(name = "id_microempresa")
    private Microempresa microempresa;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
