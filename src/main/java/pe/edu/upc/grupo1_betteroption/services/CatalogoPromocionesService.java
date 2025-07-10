package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.dtos.MicroempresaDto;
import pe.edu.upc.grupo1_betteroption.entities.CatalogoPromociones;
import pe.edu.upc.grupo1_betteroption.entities.Microempresa;
import pe.edu.upc.grupo1_betteroption.interfaces.ICatalogoPromocionesService;
import pe.edu.upc.grupo1_betteroption.repositories.CatalogoPromocionesRepository;
import pe.edu.upc.grupo1_betteroption.repositories.MicroempresaRepository;

import java.util.ArrayList;
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
        List<CatalogoPromociones> lista = catalogopromocionesrepository.findAll();
        List<CatalogoPromocionesDto> dtoLista = new ArrayList<>();

        for (CatalogoPromociones c : lista) {
            CatalogoPromocionesDto dto = modelMapper.map(c, CatalogoPromocionesDto.class);

            if (c.getMicroempresa() != null) {
                dto.setMicroempresadto(modelMapper.map(c.getMicroempresa(), MicroempresaDto.class));
            }

            dtoLista.add(dto);
        }

        return dtoLista;
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
    public CatalogoPromocionesDto actualizar(CatalogoPromocionesDto promocionesdto) {
        Long id = promocionesdto.getId_catalogopromociones();
        if (id == null) {
            throw new RuntimeException("El ID del catálogo de promociones no puede ser nulo");
        }

        CatalogoPromociones promocionExistente = catalogopromocionesrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Catalogo con id: " + id));

        promocionExistente.setNombreCatalogo(promocionesdto.getNombreCatalogo());
        promocionExistente.setDescripcion(promocionesdto.getDescripcion());
        promocionExistente.setFechaInicio(promocionesdto.getFechaInicio());
        promocionExistente.setFechaFin(promocionesdto.getFechaFin());

        if (promocionesdto.getMicroempresadto() != null && promocionesdto.getMicroempresadto().getId_microempresa() != null) {
            Microempresa microempresa = microempresarepository.findById(promocionesdto.getMicroempresadto().getId_microempresa())
                    .orElseThrow(() -> new RuntimeException("Microempresa no encontrada"));
            promocionExistente.setMicroempresa(microempresa);
        } else {
            throw new RuntimeException("Debe proporcionar el ID de la microempresa");
        }

        CatalogoPromociones actualizado = catalogopromocionesrepository.save(promocionExistente);
        return modelMapper.map(actualizado, CatalogoPromocionesDto.class);
    }

    @Override
    public CatalogoPromocionesDto obtenerPorId(Long id) {
        CatalogoPromociones catalogo = catalogopromocionesrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatalogoPromociones no encontrado con ID: " + id));

        CatalogoPromocionesDto dto = modelMapper.map(catalogo, CatalogoPromocionesDto.class);

        if (catalogo.getMicroempresa() != null) {
            dto.setMicroempresadto(modelMapper.map(catalogo.getMicroempresa(), MicroempresaDto.class));
        }

        return dto;
    }
}
