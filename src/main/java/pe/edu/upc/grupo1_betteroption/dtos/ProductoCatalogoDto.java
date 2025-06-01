package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.grupo1_betteroption.entities.CatalogoPromociones;
import pe.edu.upc.grupo1_betteroption.entities.Producto;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCatalogoDto implements Serializable {
    private Long id_productocatalogo;
    private Integer descuentoPorcentaje;
    private CatalogoPromocionesDto idCatalogoPromociones;
    private ProductoDto idProducto;
}

