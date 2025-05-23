package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.MicroempresaDto;
import pe.edu.upc.grupo1_betteroption.entities.Microempresa;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;
import pe.edu.upc.grupo1_betteroption.interfaces.IMicroempresaService;
import pe.edu.upc.grupo1_betteroption.repositories.MicroempresaRepository;
import pe.edu.upc.grupo1_betteroption.repositories.UsuarioRepository;

import java.util.List;

@Service
public class MicroempresaService implements IMicroempresaService {
    @Autowired
    private MicroempresaRepository microempresarepository;

    @Autowired
    private UsuarioRepository usuariorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MicroempresaDto grabarMicroempresa(MicroempresaDto dto) {
        Microempresa microempresa = modelMapper.map(dto, Microempresa.class);
        Usuario usuario = usuariorepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        microempresa.setUsuario(usuario);
        Microempresa guardar = microempresarepository.save(microempresa);
        return modelMapper.map(guardar, MicroempresaDto.class);
    }

    @Override
    public List<MicroempresaDto> getMicroempresas() {
        return microempresarepository.findAll().stream()
                .map(microempresa -> modelMapper.map(microempresa, MicroempresaDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (microempresarepository.existsById(id)) {
            microempresarepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la Microempresa con ID: " + id);
        }
    }
}
