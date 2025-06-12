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
public class ProductoCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_productocatalogo;

    @Column(name = "descuento_porcentaje", nullable = false)
    private Double descuentoPorcentaje;

    @ManyToOne
    @JoinColumn(name = "id_catalogopromociones")
    private CatalogoPromociones catalogoPromociones;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}