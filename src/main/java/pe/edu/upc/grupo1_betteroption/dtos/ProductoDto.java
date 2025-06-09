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
public class ProductoDto implements Serializable {
    private Long id_producto;
    private String nombreProducto;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private String imagen;
    private MicroempresaDto microempresadto;
    private CategoriaDto categoriadto;

}
