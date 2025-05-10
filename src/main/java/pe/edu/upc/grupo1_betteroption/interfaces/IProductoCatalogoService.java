package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;

import java.util.List;

public interface IProductoCatalogoService {
    public ProductoCatalogoDto grabarProductoCatalogo(ProductoCatalogoDto productocatalogodto);

    public List<ProductoCatalogoDto> getProductosCatalogos();
}
