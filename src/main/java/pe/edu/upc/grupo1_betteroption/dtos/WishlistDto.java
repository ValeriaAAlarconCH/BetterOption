package pe.edu.upc.grupo1_betteroption.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto implements Serializable {
    private Long id_wishlist;
    private LocalDate fechaAgregado;
    private UsuarioDto idUsuario;
    private ProductoDto idProducto;
}
