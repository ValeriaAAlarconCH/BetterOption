package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;

import java.util.List;

public interface IProductoService {
    public ProductoDto grabarProducto(ProductoDto productodto);

    public List<ProductoDto> getProductos();

    void eliminarProducto(Long id);

}
