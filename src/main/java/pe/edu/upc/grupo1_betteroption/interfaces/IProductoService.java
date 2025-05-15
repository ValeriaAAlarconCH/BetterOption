package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;

import java.util.List;

public interface IProductoService {
    public ProductoDto grabarProducto(ProductoDto productodto);

    public List<ProductoDto> getProductos();

    void eliminar(Long id);

    List<ProductoDto> buscarPorNombre(String nombre);

//    List<ProductoDto> filtrarPorCategoria(Long idCategoria);

    List<ProductoDto> filtrarPorPrecio(Double min, Double max);

//    List<ProductoDto> obtenerPromocionesActivas();
//
//    List<ProductoDto> obtenerWishlistUsuario(Long idUsuario);
//
//    List<ProductoDeseadoDto> obtenerProductosMasDeseados();

}
