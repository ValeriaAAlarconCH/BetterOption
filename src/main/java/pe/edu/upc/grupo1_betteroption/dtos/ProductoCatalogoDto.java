package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCatalogoDto implements Serializable {
    private Long id_productocatalogo;
    private Integer descuentoPorcentaje;
    private CatalogoPromocionesDto catalogopromocionesdto;
    private ProductoDto productodto;
}

