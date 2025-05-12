package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.entities.ProductoCatalogo;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoCatalogoService;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoCatalogoRepository;

import java.util.List;

@Service
public class ProductoCatalogoService implements IProductoCatalogoService {
    @Autowired
    private ProductoCatalogoRepository productocatalogorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductoCatalogoDto grabarProductoCatalogo(ProductoCatalogoDto productocatalogodto) {
        ProductoCatalogo productocatalogo = modelMapper.map(productocatalogodto, ProductoCatalogo.class);
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
            throw new RuntimeException("No se encontró el ID: " + id);
        }
    }
}
