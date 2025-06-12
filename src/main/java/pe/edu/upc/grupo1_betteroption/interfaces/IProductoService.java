package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;

import java.util.List;

public interface IProductoService {
    public ProductoDto grabarProducto(ProductoDto productodto);

    public List<ProductoDto> getProductos();

    void eliminar(Long id);

    public ProductoDto actualizar(ProductoDto productodto);

    List<ProductoDto> buscarPorNombre(String nombre);

    List<ProductoDto> filtrarPorPrecio(Double min, Double max);

    public ProductoDto obtenerPorId(Long id);
}
