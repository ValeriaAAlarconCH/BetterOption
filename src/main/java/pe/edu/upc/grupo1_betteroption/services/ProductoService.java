package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoService;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductoDto grabarProducto(ProductoDto productodto) {
        Producto producto = modelMapper.map(productodto, Producto.class);
        Producto guardar = productorepository.save(producto);
        return modelMapper.map(guardar, ProductoDto.class);
    }

    @Override
    public List<ProductoDto> getProductos() {
        return productorepository.findAll().stream()
                .map(producto -> modelMapper.map(producto, ProductoDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (productorepository.existsById(id)) {
            productorepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el producto con ID: " + id);
        }
    }

    @Override
    public List<ProductoDto> buscarPorNombre(String nombre) {
        return productorepository.findByNombreProductoContainingIgnoreCase(nombre)
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }

//    @Override
//    public List<ProductoDto> filtrarPorCategoria(Long idCategoria) {
//        return productorepository.findByCategoria_Id_categoria(idCategoria)
//                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
//    }

    @Override
    public List<ProductoDto> filtrarPorPrecio(Double min, Double max) {
        return productorepository.findByPrecioBetween(min, max)
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDto> obtenerPromocionesActivas() {
        return productorepository.findProductosConPromocionesActivas()
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDto> obtenerWishlistUsuario(Long idUsuario) {
        return productorepository.findWishlistByUsuario(idUsuario)
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDeseadoDto> obtenerProductosMasDeseados() {
        return productorepository.findProductosMasDeseados();
    }
}
