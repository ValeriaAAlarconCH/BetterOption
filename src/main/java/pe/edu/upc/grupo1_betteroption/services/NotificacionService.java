package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.NotificacionDto;
import pe.edu.upc.grupo1_betteroption.entities.Notificacion;
import pe.edu.upc.grupo1_betteroption.entities.Usuario;
import pe.edu.upc.grupo1_betteroption.interfaces.INotificacionService;
import pe.edu.upc.grupo1_betteroption.repositories.NotificacionRepository;
import pe.edu.upc.grupo1_betteroption.repositories.UsuarioRepository;

import java.util.List;

@Service
public class NotificacionService implements INotificacionService {
    @Autowired
    private NotificacionRepository notificacionrepository;

    @Autowired
    private UsuarioRepository usuariorepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public NotificacionDto grabarNotificacion(NotificacionDto dto) {
        Notificacion notificacion = modelMapper.map(dto, Notificacion.class);

        if (dto.getUsuariodto() != null && dto.getUsuariodto().getId_usuario() != null) {
            Usuario usuario = usuariorepository.findById(dto.getUsuariodto().getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuariodto().getId_usuario()));
            notificacion.setUsuario(usuario);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del usuario");
        }

        Notificacion guardado = notificacionrepository.save(notificacion);
        return modelMapper.map(guardado, NotificacionDto.class);
    }

    @Override
    public List<NotificacionDto> getNotificaciones() {
        return modelMapper.map(notificacionrepository.findAll(), List.class);
    }

    @Override
    public void eliminar(Long id) {
        if (notificacionrepository.existsById(id)) {
            notificacionrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la Notificación con ID: " + id);
        }
    }

    @Override
    public NotificacionDto actualizar(NotificacionDto notificaciondto) {
        Long id = notificaciondto.getId_notificacion();
        if (id == null) {
            throw new RuntimeException("El ID de la notificación no puede ser nulo");
        }

        Notificacion notificacionExistente = notificacionrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la Notificacion con id: " + id));

        notificacionExistente.setMensaje(notificaciondto.getMensaje());
        notificacionExistente.setTipo(notificaciondto.getTipo());
        notificacionExistente.setLeido(notificaciondto.getLeido());

        if (notificaciondto.getUsuariodto() != null && notificaciondto.getUsuariodto().getId_usuario() != null) {
            Usuario usuario = usuariorepository.findById(notificaciondto.getUsuariodto().getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            notificacionExistente.setUsuario(usuario);
        } else {
            throw new RuntimeException("Debe proporcionar el ID del usuario");
        }

        Notificacion actualizado = notificacionrepository.save(notificacionExistente);
        return modelMapper.map(actualizado, NotificacionDto.class);
    }

    @Override
    public NotificacionDto obtenerPorId(Long id) {
        Notificacion notificacion = notificacionrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada con ID: " + id));
        return modelMapper.map(notificacion, NotificacionDto.class);
    }
}
