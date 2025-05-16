package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.ProductoCatalogo;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoCatalogoService;
import pe.edu.upc.grupo1_betteroption.repositories.CatalogoPromocionesRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoCatalogoRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoCatalogoService implements IProductoCatalogoService {
    @Autowired
    private ProductoCatalogoRepository productocatalogorepository;

    @Autowired
    private CatalogoPromocionesRepository catalogoPromocionesrepository;

    @Autowired
    private ProductoRepository productorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductoCatalogoDto grabarProductoCatalogo(ProductoCatalogoDto dto) {
        ProductoCatalogo productocatalogo = modelMapper.map(dto, ProductoCatalogo.class);

        productocatalogo.setCatalogoPromociones(
                catalogoPromocionesrepository.findById(dto.getIdCatalogoPromociones())
                        .orElseThrow(() -> new RuntimeException("Catálogo no encontrado"))
        );

        productocatalogo.setProducto(
                productorepository.findById(dto.getIdProducto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"))
        );

        ProductoCatalogo guardar = productocatalogorepository.save(productocatalogo);
        return modelMapper.map(guardar, ProductoCatalogoDto.class);
    }

    @Override
    public List<ProductoCatalogoDto> getProductosCatalogos() {
        return productocatalogorepository.findAll().stream()
                .map(productocatalogo -> modelMapper.map(productocatalogo, ProductoCatalogoDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (productocatalogorepository.existsById(id)) {
            productocatalogorepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el ProductoCatalogo con ID: " + id);
        }
    }

    @Override
    public List<ProductoDto> obtenerProductosConPromocionesActivas() {
        return productocatalogorepository.findProductosConPromocionesActivas()
                .stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .toList();
    }

}
