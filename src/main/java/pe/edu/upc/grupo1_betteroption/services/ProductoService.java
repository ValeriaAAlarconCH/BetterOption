package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoService;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.List;

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
}
