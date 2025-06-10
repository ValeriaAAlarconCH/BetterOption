package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;
import pe.edu.upc.grupo1_betteroption.interfaces.IWishlistService;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;
import pe.edu.upc.grupo1_betteroption.repositories.UsuarioRepository;
import pe.edu.upc.grupo1_betteroption.repositories.WishlistRepository;

import java.util.List;

@Service
public class WishlistService implements IWishlistService {
    @Autowired
    private WishlistRepository wishlistrepository;

    @Autowired
    private UsuarioRepository usuariorepository;

    @Autowired
    private ProductoRepository productorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WishlistDto grabarWishlist(WishlistDto wishlistdto) {
        Wishlist wishlist = modelMapper.map(wishlistdto, Wishlist.class);

        if (wishlistdto.getUsuariodto() != null && wishlistdto.getUsuariodto().getId_usuario() != null) {
            Usuario usuario = usuariorepository.findById(wishlistdto.getUsuariodto().getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + wishlistdto.getUsuariodto().getId_usuario()));
            wishlist.setUsuario(usuario);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del usuario");
        }

        if (wishlistdto.getProductodto() != null && wishlistdto.getProductodto().getId_producto() != null) {
            Producto producto = productorepository.findById(wishlistdto.getProductodto().getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + wishlistdto.getProductodto().getId_producto()));
            wishlist.setProducto(producto);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del producto");
        }

        Wishlist guardado = wishlistrepository.save(wishlist);
        return modelMapper.map(guardado, WishlistDto.class);
    }

    @Override
    public List<WishlistDto> getWishlists() {
        return modelMapper.map(wishlistrepository.findAll(), List.class);
    }

    @Override
    public void eliminar(Long id) {
        if (wishlistrepository.existsById(id)) {
            wishlistrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el wishlist con ID: " + id);
        }
    }

    @Override
    public WishlistDto actualizar(Long id, WishlistDto wishlistdto) {
        Wishlist wishlistExistente = wishlistrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la Wishlist con id: " + id));

        Usuario usuario = usuariorepository.findById(wishlistdto.getUsuariodto().getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        wishlistExistente.setUsuario(usuario);

        Producto producto = productorepository.findById(wishlistdto.getProductodto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        wishlistExistente.setProducto(producto);

        Wishlist actualizado = wishlistrepository.save(wishlistExistente);
        return modelMapper.map(actualizado, WishlistDto.class);
    }

    @Override
    public List<ProductoDto> obtenerWishlistPorUsuario(Long idUsuario) {
        return wishlistrepository.findProductosEnWishlistPorUsuario(idUsuario)
                .stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .toList();
    }

    @Override
    public List<ProductoDeseadoDto> obtenerProductosMasDeseados() {
        return wishlistrepository.findProductosMasDeseados();
    }

}
