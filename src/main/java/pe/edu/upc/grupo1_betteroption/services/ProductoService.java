package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoService;
import pe.edu.upc.grupo1_betteroption.repositories.CategoriaRepository;
import pe.edu.upc.grupo1_betteroption.repositories.MicroempresaRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productorepository;

    @Autowired
    private MicroempresaRepository microempresarepository;

    @Autowired
    private CategoriaRepository categoriarepository;

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
        return modelMapper.map(productorepository.findAll(), List.class);
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
    public ProductoDto actualizar(Long id, ProductoDto productodto) {
        Producto productoExistente = productorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Producto con id: " + id));

        productoExistente.setNombreProducto(productodto.getNombreProducto());
        productoExistente.setDescripcion(productodto.getDescripcion());
        productoExistente.setPrecio(Double.valueOf(productodto.getPrecio()));
        productoExistente.setStock(productodto.getStock());
        productoExistente.setImagen(productodto.getImagen());

        productoExistente.setMicroempresa(microempresarepository.findById(id).get());

        productoExistente.setCategoria(categoriarepository.findById(id).get());

        Producto actualizado = productorepository.save(productoExistente);
        return modelMapper.map(actualizado, ProductoDto.class);

    }

    @Override
    public List<ProductoDto> buscarPorNombre(String nombre) {
        return productorepository.findByNombreProducto(nombre)
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<ProductoDto> filtrarPorPrecio(Double min, Double max) {
        return productorepository.findByPrecioBetween(min, max)
                .stream().map(p -> modelMapper.map(p, ProductoDto.class)).collect(Collectors.toList());
    }


}
