package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.entities.CatalogoPromociones;
import pe.edu.upc.grupo1_betteroption.entities.Microempresa;
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
        CatalogoPromociones catalogopromociones = modelMapper.map(dto, CatalogoPromociones.class);

        if (dto.getMicroempresadto() != null && dto.getMicroempresadto().getId_microempresa() != null) {
            Microempresa microempresa = microempresarepository.findById(dto.getMicroempresadto().getId_microempresa())
                    .orElseThrow(() -> new RuntimeException("Microempresa no encontrado con ID: " + dto.getMicroempresadto().getId_microempresa()));
            catalogopromociones.setMicroempresa(microempresa);
        } else {
            throw new RuntimeException("Debe proporcionar el ID de la microempresa");
        }

        CatalogoPromociones guardado = catalogopromocionesrepository.save(catalogopromociones);
        return modelMapper.map(guardado, CatalogoPromocionesDto.class);
    }

    @Override
    public List<CatalogoPromocionesDto> getCatalogosPromociones() {
        return modelMapper.map(catalogopromocionesrepository.findAll(), List.class);
    }

    @Override
    public void eliminar(Long id) {
        if (catalogopromocionesrepository.existsById(id)) {
            catalogopromocionesrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el CatalogoPromociones con ID: " + id);
        }
    }

    @Override
    public CatalogoPromocionesDto actualizar(Long id, CatalogoPromocionesDto promocionesdto) {
        CatalogoPromociones promocionExistente = catalogopromocionesrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Catalogo con id: " + id));

        promocionExistente.setNombreCatalogo(promocionesdto.getNombreCatalogo());
        promocionExistente.setDescripcion(promocionesdto.getDescripcion());
        promocionExistente.setFechaInicio(promocionesdto.getFechaInicio());
        promocionExistente.setFechaFin(promocionesdto.getFechaFin());

        Microempresa microempresa = microempresarepository.findById(promocionesdto.getMicroempresadto().getId_microempresa())
                .orElseThrow(() -> new RuntimeException("Microempresa no encontrado"));
        promocionExistente.setMicroempresa(microempresa);

        CatalogoPromociones actualizado = catalogopromocionesrepository.save(promocionExistente);
        return modelMapper.map(actualizado, CatalogoPromocionesDto.class);
    }
}
