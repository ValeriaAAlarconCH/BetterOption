package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.CategoriaDto;

import java.util.List;

public interface ICategoriaService {
    public CategoriaDto grabarCategoria(CategoriaDto categoriadto);

    public List<CategoriaDto> getCategorias();
}
