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
public class MicroempresaDto implements Serializable {
    private Long id_microempresa;
    private String nombreNegocio;
    private String rubro;
    private String direccion;
    private Integer telefono;
    private String email;
    private String descripcion;
    private UsuarioDto usuariodto;
}
