package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto implements Serializable {
    private Long id_wishlist;
    private LocalDate fechaAgregado;
    private UsuarioDto usuariodto;
    private List<ProductoDto> productosdto;
}
