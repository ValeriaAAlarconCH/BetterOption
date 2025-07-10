package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;

import java.util.List;

public interface IWishlistService {
    public WishlistDto grabarWishlist(WishlistDto wishlistdto);

    public List<WishlistDto> getWishlists();

    void eliminar(Long id);

    WishlistDto actualizar(WishlistDto wishlistdto);

    List<ProductoDto> obtenerWishlistPorUsuario(Long idUsuario);

    List<ProductoDeseadoDto> obtenerProductosMasDeseados();

    public WishlistDto obtenerPorId(Long id);

    WishlistDto agregarProductoAWishlist(Long idWishlist, Long idProducto);
}
