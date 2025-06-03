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
        Notificacion guardar = notificacionrepository.save(notificacion);
        return modelMapper.map(guardar, NotificacionDto.class);
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
    public NotificacionDto actualizar(Long id, NotificacionDto notificaciondto) {
        Notificacion notificacionExistente = notificacionrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la Notificacion con id: " + id));

        notificacionExistente.setMensaje(notificaciondto.getMensaje());
        notificacionExistente.setTipo(notificaciondto.getTipo());
        notificacionExistente.setLeido(notificaciondto.getLeido());

        Usuario usuario = usuariorepository.findById(id).get();
        notificacionExistente.setUsuario(usuario);

        Notificacion actualizado = notificacionrepository.save(notificacionExistente);
        return modelMapper.map(actualizado, NotificacionDto.class);
    }
}
