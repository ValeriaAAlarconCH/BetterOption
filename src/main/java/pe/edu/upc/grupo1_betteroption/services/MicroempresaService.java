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

        if (dto.getUsuariodto() != null && dto.getUsuariodto().getId_usuario() != null) {
            Usuario usuario = usuariorepository.findById(dto.getUsuariodto().getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuariodto().getId_usuario()));
            microempresa.setUsuario(usuario);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del usuario");
        }

        Microempresa guardado = microempresarepository.save(microempresa);
        return modelMapper.map(guardado, MicroempresaDto.class);

    }

    @Override
    public List<MicroempresaDto> getMicroempresas() {
        return modelMapper.map(microempresarepository.findAll(), List.class);
    }

    @Override
    public void eliminar(Long id) {
        if (microempresarepository.existsById(id)) {
            microempresarepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la Microempresa con ID: " + id);
        }
    }

    @Override
    public MicroempresaDto actualizar(Long id, MicroempresaDto microempresadto) {
        Microempresa microempresaExistente = microempresarepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la Microempresa con id: " + id));

        microempresaExistente.setNombreNegocio(microempresadto.getNombreNegocio());
        microempresaExistente.setRubro(microempresadto.getRubro());
        microempresaExistente.setDireccion( microempresadto.getDireccion());
        microempresaExistente.setTelefono(microempresadto.getTelefono());
        microempresaExistente.setEmail(microempresadto.getEmail());
        microempresaExistente.setDescripcion(microempresadto.getDescripcion());

        Usuario usuario = usuariorepository.findById(microempresadto.getUsuariodto().getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        microempresaExistente.setUsuario(usuario);

        Microempresa actualizado = microempresarepository.save(microempresaExistente);
        return modelMapper.map(actualizado, MicroempresaDto.class);

    }
}
