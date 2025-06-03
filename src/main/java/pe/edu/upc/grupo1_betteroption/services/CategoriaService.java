package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CategoriaDto;
import pe.edu.upc.grupo1_betteroption.entities.Categoria;
import pe.edu.upc.grupo1_betteroption.interfaces.ICategoriaService;
import pe.edu.upc.grupo1_betteroption.repositories.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private CategoriaRepository categoriarepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoriaDto grabarCategoria(CategoriaDto categoriadto) {
        Categoria categoria = modelMapper.map(categoriadto, Categoria.class);
        Categoria guardar = categoriarepository.save(categoria);
        return modelMapper.map(guardar, CategoriaDto.class);
    }

    @Override
    public List<CategoriaDto> getCategorias() {
        return modelMapper.map(categoriarepository.findAll(), List.class);
    }

    @Override
    public void eliminar(Long id) {
        if (categoriarepository.existsById(id)) {
            categoriarepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la categoria con ID: " + id);
        }
    }

    @Override
    public CategoriaDto actualizar(Long id, CategoriaDto categoriadto) {
        Categoria categoriaExistente = categoriarepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontró el usuario con ID: " + id));

        categoriaExistente.setNombreCategoria(categoriadto.getNombreCategoria());
        categoriaExistente.setDescripcion(categoriadto.getDescripcion());

        Categoria actualizar = categoriarepository.save(categoriaExistente);
        return modelMapper.map(actualizar, CategoriaDto.class);
    }
}
