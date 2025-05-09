package pe.edu.upc.grupo1_betteroption.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.grupo1_betteroption.entities.Categoria;
import pe.edu.upc.grupo1_betteroption.entities.Microempresa;

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
    private Microempresa microempresa;
    private Categoria categoria;

}
