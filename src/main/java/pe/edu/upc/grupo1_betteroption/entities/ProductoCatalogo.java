package pe.edu.upc.grupo1_betteroption.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ProductoCatalogo")
public class ProductoCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_productocatalogo", nullable = false)
    private Long id_productocatalogo;

    @Column(name = "descuento_porcentaje", nullable = false)
    private Integer descuentoPorcentaje;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_catalogopromociones", nullable = false)
    private CatalogoPromociones catalogoPromociones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}
