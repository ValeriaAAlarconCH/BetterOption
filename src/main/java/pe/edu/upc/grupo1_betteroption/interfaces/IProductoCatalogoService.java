package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;

import java.util.List;

public interface IProductoCatalogoService {
    public ProductoCatalogoDto grabarProductoCatalogo(ProductoCatalogoDto productocatalogodto);

    public List<ProductoCatalogoDto> getProductosCatalogos();

    void eliminar(Long id);

    public ProductoCatalogoDto actualizar(Long id, ProductoCatalogoDto productocatalogodto);

    List<ProductoDto> obtenerProductosConPromocionesActivas();

}
