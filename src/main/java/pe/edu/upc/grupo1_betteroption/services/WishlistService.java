package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.dtos.UsuarioDto;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;
import pe.edu.upc.grupo1_betteroption.interfaces.IWishlistService;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;
import pe.edu.upc.grupo1_betteroption.repositories.UsuarioRepository;
import pe.edu.upc.grupo1_betteroption.repositories.WishlistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Wishlist wishlist = new Wishlist();
        wishlist.setFechaAgregado(wishlistdto.getFechaAgregado());

        if (wishlistdto.getUsuariodto() != null && wishlistdto.getUsuariodto().getId_usuario() != null) {
            Usuario usuario = usuariorepository.findById(wishlistdto.getUsuariodto().getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + wishlistdto.getUsuariodto().getId_usuario()));
            wishlist.setUsuario(usuario);
        }

        List<Producto> productos = new ArrayList<>();
        if (wishlistdto.getProductosdto() != null) {
            for (ProductoDto p : wishlistdto.getProductosdto()) {
                Producto producto = productorepository.findById(p.getId_producto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + p.getId_producto()));
                productos.add(producto);
            }
        }
        wishlist.setProductos(productos);

        Wishlist guardado = wishlistrepository.save(wishlist);

        WishlistDto respuesta = new WishlistDto();
        respuesta.setId_wishlist(guardado.getId_wishlist());
        respuesta.setFechaAgregado(guardado.getFechaAgregado());
        respuesta.setUsuariodto(modelMapper.map(guardado.getUsuario(), UsuarioDto.class));
        respuesta.setProductosdto(guardado.getProductos().stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .collect(Collectors.toList()));

        return respuesta;
    }

    @Override
    public List<WishlistDto> getWishlists() {
        List<Wishlist> lista = wishlistrepository.findAll();
        List<WishlistDto> dtoLista = new ArrayList<>();

        for (Wishlist w : lista) {
            WishlistDto dto = new WishlistDto();
            dto.setId_wishlist(w.getId_wishlist());
            dto.setFechaAgregado(w.getFechaAgregado());
            dto.setUsuariodto(modelMapper.map(w.getUsuario(), UsuarioDto.class));
            dto.setProductosdto(w.getProductos().stream()
                    .map(p -> modelMapper.map(p, ProductoDto.class))
                    .collect(Collectors.toList()));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    @Override
    public WishlistDto obtenerPorId(Long id) {
        Wishlist w = wishlistrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist no encontrado con ID: " + id));
        WishlistDto dto = new WishlistDto();
        dto.setId_wishlist(w.getId_wishlist());
        dto.setFechaAgregado(w.getFechaAgregado());
        dto.setUsuariodto(modelMapper.map(w.getUsuario(), UsuarioDto.class));
        dto.setProductosdto(w.getProductos().stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public WishlistDto agregarProductoAWishlist(Long idWishlist, Long idProducto) {
        Wishlist wishlist = wishlistrepository.findById(idWishlist)
                .orElseThrow(() -> new RuntimeException("Wishlist no encontrada con ID: " + idWishlist));

        Producto producto = productorepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));

        wishlist.getProductos().add(producto);
        Wishlist actualizado = wishlistrepository.save(wishlist);
        return modelMapper.map(actualizado, WishlistDto.class);
    }

    @Override
    public WishlistDto actualizar(WishlistDto wishlistdto) {
        Wishlist wishlistExistente = wishlistrepository.findById(wishlistdto.getId_wishlist())
                .orElseThrow(() -> new RuntimeException("Wishlist no encontrada"));

        wishlistExistente.setFechaAgregado(wishlistdto.getFechaAgregado());

        Usuario usuario = usuariorepository.findById(wishlistdto.getUsuariodto().getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        wishlistExistente.setUsuario(usuario);

        List<Producto> nuevosProductos = new ArrayList<>();
        for (ProductoDto p : wishlistdto.getProductosdto()) {
            Producto producto = productorepository.findById(p.getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            nuevosProductos.add(producto);
        }
        wishlistExistente.setProductos(nuevosProductos);

        Wishlist actualizado = wishlistrepository.save(wishlistExistente);

        WishlistDto dto = new WishlistDto();
        dto.setId_wishlist(actualizado.getId_wishlist());
        dto.setFechaAgregado(actualizado.getFechaAgregado());
        dto.setUsuariodto(modelMapper.map(actualizado.getUsuario(), UsuarioDto.class));
        dto.setProductosdto(actualizado.getProductos().stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .collect(Collectors.toList()));
        return dto;
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
