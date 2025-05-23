package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.entities.CatalogoPromociones;
import pe.edu.upc.grupo1_betteroption.interfaces.ICatalogoPromocionesService;
import pe.edu.upc.grupo1_betteroption.repositories.CatalogoPromocionesRepository;
import pe.edu.upc.grupo1_betteroption.repositories.MicroempresaRepository;

import java.util.List;

@Service
public class CatalogoPromocionesService implements ICatalogoPromocionesService {
    @Autowired
    private CatalogoPromocionesRepository catalogopromocionesrepository;

    @Autowired
    private MicroempresaRepository microempresarepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CatalogoPromocionesDto grabarCatalogoPromociones(CatalogoPromocionesDto dto) {
        CatalogoPromociones catalogo = modelMapper.map(dto, CatalogoPromociones.class);

        catalogo.setMicroempresa(
                microempresarepository.findById(dto.getIdMicroempresa())
                        .orElseThrow(() -> new RuntimeException("Microempresa no encontrada"))
        );

        CatalogoPromociones guardar = catalogopromocionesrepository.save(catalogo);
        return modelMapper.map(guardar, CatalogoPromocionesDto.class);
    }

    @Override
    public List<CatalogoPromocionesDto> getCatalogosPromociones() {
        return catalogopromocionesrepository.findAll().stream()
                .map(catalogopromociones -> modelMapper.map(catalogopromociones, CatalogoPromocionesDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (catalogopromocionesrepository.existsById(id)) {
            catalogopromocionesrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el CatalogoPromociones con ID: " + id);
        }
    }
}
