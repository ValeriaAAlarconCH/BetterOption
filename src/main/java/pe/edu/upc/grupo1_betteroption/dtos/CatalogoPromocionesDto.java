package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoPromocionesDto implements Serializable {
    private Long id_catalogopromociones;
    private String nombreCatalogo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private MicroempresaDto microempresadto;
}
