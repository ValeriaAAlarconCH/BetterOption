package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.MicroempresaDto;

import java.util.List;

public interface IMicroempresaService {
    public MicroempresaDto grabarMicroempresa(MicroempresaDto microempresadto);

    public List<MicroempresaDto> getMicroempresas();
}
