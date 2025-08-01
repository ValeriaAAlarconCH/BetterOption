package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CategoriaDto;
import pe.edu.upc.grupo1_betteroption.dtos.MicroempresaDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.Categoria;
import pe.edu.upc.grupo1_betteroption.entities.Microempresa;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoService;
import pe.edu.upc.grupo1_betteroption.repositories.CategoriaRepository;
import pe.edu.upc.grupo1_betteroption.repositories.MicroempresaRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.ArrayList;
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

        if (productodto.getMicroempresadto() != null && productodto.getMicroempresadto().getId_microempresa() != null) {
            Microempresa microempresa = microempresarepository.findById(productodto.getMicroempresadto().getId_microempresa())
                    .orElseThrow(() -> new RuntimeException("Microempresa no encontrado con ID: " + productodto.getMicroempresadto().getId_microempresa()));
            producto.setMicroempresa(microempresa);
        } else {
            throw new RuntimeException("Debe proporcionar el ID de la microempresa");
        }

        if (productodto.getCategoriadto() != null && productodto.getCategoriadto().getId_categoria() != null) {
            Categoria categoria = categoriarepository.findById(productodto.getCategoriadto().getId_categoria())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + productodto.getCategoriadto().getId_categoria()));
            producto.setCategoria(categoria);
        } else {
            throw new RuntimeException("Debe proporcionar el ID de la categoria");
        }

        Producto guardado = productorepository.save(producto);
        ProductoDto respuesta = modelMapper.map(guardado, ProductoDto.class);
        respuesta.setMicroempresadto(modelMapper.map(guardado.getMicroempresa(), MicroempresaDto.class));
        respuesta.setCategoriadto(modelMapper.map(guardado.getCategoria(), CategoriaDto.class));
        return respuesta;
    }

    @Override
    public List<ProductoDto> getProductos() {
        List<Producto> lista = productorepository.findAll();
        List<ProductoDto> dtoLista = new ArrayList<>();

        for (Producto p : lista) {
            ProductoDto dto = modelMapper.map(p, ProductoDto.class);

            if (p.getMicroempresa() != null) {
                dto.setMicroempresadto(modelMapper.map(p.getMicroempresa(), MicroempresaDto.class));
            }

            if (p.getCategoria() != null) {
                dto.setCategoriadto(modelMapper.map(p.getCategoria(), CategoriaDto.class));
            }

            dtoLista.add(dto);
        }

        return dtoLista;
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
    public ProductoDto actualizar(ProductoDto productodto) {
        Long id = productodto.getId_producto();
        if (id == null) {
            throw new RuntimeException("El ID del producto no puede ser nulo");
        }

        Producto productoExistente = productorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Producto con id: " + id));

        productoExistente.setNombreProducto(productodto.getNombreProducto());
        productoExistente.setDescripcion(productodto.getDescripcion());
        productoExistente.setPrecio(Double.valueOf(productodto.getPrecio()));
        productoExistente.setStock(productodto.getStock());
        productoExistente.setImagen(productodto.getImagen());

        Microempresa microempresa = microempresarepository.findById(productodto.getMicroempresadto().getId_microempresa())
                .orElseThrow(() -> new RuntimeException("Microempresa no encontrado"));
        productoExistente.setMicroempresa(microempresa);

        Categoria categoria = categoriarepository.findById(productodto.getCategoriadto().getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        productoExistente.setCategoria(categoria);

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

    @Override
    public ProductoDto obtenerPorId(Long id) {
        Producto producto = productorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return modelMapper.map(producto, ProductoDto.class);
    }
}
