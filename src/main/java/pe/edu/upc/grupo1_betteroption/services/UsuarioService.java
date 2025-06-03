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
        return usuariorepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (usuariorepository.existsById(id)) {
            usuariorepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public UsuarioDto actualizar(Long id, UsuarioDto usuariodto) {
        Usuario usuarioExistente = usuariorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con ID: " + id));

        usuarioExistente.setNombre(usuariodto.getNombre());
        usuarioExistente.setCorreo(usuariodto.getCorreo());
        usuarioExistente.setPassword(usuariodto.getPassword());

        Usuario actualizado = usuariorepository.save(usuarioExistente);
        return modelMapper.map(actualizado, UsuarioDto.class);
    }


}