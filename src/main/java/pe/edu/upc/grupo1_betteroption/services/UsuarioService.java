package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.UsuarioDto;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;
import pe.edu.upc.grupo1_betteroption.interfaces.IUsuarioService;
import pe.edu.upc.grupo1_betteroption.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuariorepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioDto grabarUsuario(UsuarioDto usuariodto) {
        Usuario usuario = modelMapper.map(usuariodto, Usuario.class);
        Usuario guardar = usuariorepository.save(usuario);
        return modelMapper.map(guardar, UsuarioDto.class);
    }

    @Override
    public List<UsuarioDto> getUsuarios() {
        return modelMapper.map(usuariorepository.findAll(), List.class);
    }


}
