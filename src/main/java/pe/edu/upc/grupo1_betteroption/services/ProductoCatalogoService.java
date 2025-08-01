package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.entities.CatalogoPromociones;
import pe.edu.upc.grupo1_betteroption.entities.Producto;
import pe.edu.upc.grupo1_betteroption.entities.ProductoCatalogo;
import pe.edu.upc.grupo1_betteroption.interfaces.IProductoCatalogoService;
import pe.edu.upc.grupo1_betteroption.repositories.CatalogoPromocionesRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoCatalogoRepository;
import pe.edu.upc.grupo1_betteroption.repositories.ProductoRepository;

import java.util.ArrayList;
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

        if (dto.getCatalogopromocionesdto() != null && dto.getCatalogopromocionesdto().getId_catalogopromociones() != null) {
            CatalogoPromociones catalogopromociones = catalogoPromocionesrepository.findById(dto.getCatalogopromocionesdto().getId_catalogopromociones())
                    .orElseThrow(() -> new RuntimeException("CatalogoPromociones no encontrado con ID: " + dto.getCatalogopromocionesdto().getId_catalogopromociones()));
            productocatalogo.setCatalogoPromociones(catalogopromociones);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del catalogopromociones");
        }

        if (dto.getProductodto() != null && dto.getProductodto().getId_producto() != null) {
            Producto producto = productorepository.findById(dto.getProductodto().getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + dto.getProductodto().getId_producto()));
            productocatalogo.setProducto(producto);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del producto");
        }

        ProductoCatalogo guardado = productocatalogorepository.save(productocatalogo);
        return modelMapper.map(guardado, ProductoCatalogoDto.class);
    }

    @Override
    public List<ProductoCatalogoDto> getProductosCatalogos() {
        List<ProductoCatalogo> lista = productocatalogorepository.findAll();
        List<ProductoCatalogoDto> dtoLista = new ArrayList<>();

        for (ProductoCatalogo pc : lista) {
            ProductoCatalogoDto dto = modelMapper.map(pc, ProductoCatalogoDto.class);

            if (pc.getProducto() != null) {
                dto.setProductodto(modelMapper.map(pc.getProducto(), ProductoDto.class));
            }
            if (pc.getCatalogoPromociones() != null) {
                dto.setCatalogopromocionesdto(modelMapper.map(pc.getCatalogoPromociones(), CatalogoPromocionesDto.class));
            }

            dtoLista.add(dto);
        }

        return dtoLista;
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
    public ProductoCatalogoDto actualizar(ProductoCatalogoDto productocatalogodto) {
        Long id = productocatalogodto.getId_productocatalogo();
        if (id == null) {
            throw new RuntimeException("El ID del producto catálogo no puede ser nulo");
        }

        ProductoCatalogo catalogoExistente = productocatalogorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro con id: " + id));

        catalogoExistente.setDescuentoPorcentaje(Double.valueOf(productocatalogodto.getDescuentoPorcentaje()));

        CatalogoPromociones catalogopromociones = catalogoPromocionesrepository.findById(productocatalogodto.getCatalogopromocionesdto().getId_catalogopromociones())
                .orElseThrow(() -> new RuntimeException("CatalogoPromociones no encontrado"));
        catalogoExistente.setCatalogoPromociones(catalogopromociones);

        Producto producto = productorepository.findById(productocatalogodto.getProductodto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        catalogoExistente.setProducto(producto);

        ProductoCatalogo actualizado = productocatalogorepository.save(catalogoExistente);
        return modelMapper.map(actualizado, ProductoCatalogoDto.class);
    }

    @Override
    public List<ProductoDto> obtenerProductosConPromocionesActivas() {
        return productocatalogorepository.findProductosConPromocionesActivas()
                .stream()
                .map(p -> modelMapper.map(p, ProductoDto.class))
                .toList();
    }

    @Override
    public ProductoCatalogoDto obtenerPorId(Long id) {
        ProductoCatalogo pc = productocatalogorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductoCatalogo no encontrado con ID: " + id));

        ProductoCatalogoDto dto = modelMapper.map(pc, ProductoCatalogoDto.class);

        if (pc.getProducto() != null) {
            dto.setProductodto(modelMapper.map(pc.getProducto(), ProductoDto.class));
        }
        if (pc.getCatalogoPromociones() != null) {
            dto.setCatalogopromocionesdto(modelMapper.map(pc.getCatalogoPromociones(), CatalogoPromocionesDto.class));
        }

        return dto;
    }
}
