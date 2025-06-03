package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;

import java.util.List;

public interface ICatalogoPromocionesService {
    public CatalogoPromocionesDto grabarCatalogoPromociones(CatalogoPromocionesDto catalogopromocionesdto);

    public List<CatalogoPromocionesDto> getCatalogosPromociones();

    void eliminar(Long id);

    public CatalogoPromocionesDto actualizar(Long id, CatalogoPromocionesDto promocionesdto);
}
