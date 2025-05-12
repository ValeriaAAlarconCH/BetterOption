package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.NotificacionDto;
import pe.edu.upc.grupo1_betteroption.entities.Notificacion;
import pe.edu.upc.grupo1_betteroption.interfaces.INotificacionService;
import pe.edu.upc.grupo1_betteroption.repositories.NotificacionRepository;

import java.util.List;

@Service
public class NotificacionService implements INotificacionService {
    @Autowired
    private NotificacionRepository notificacionrepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public NotificacionDto grabarNotificacion(NotificacionDto notificaciondto) {
        Notificacion notificacion = modelMapper.map(notificaciondto, Notificacion.class);
        Notificacion guardar = notificacionrepository.save(notificacion);
        return modelMapper.map(guardar, NotificacionDto.class);
    }

    @Override
    public List<NotificacionDto> getNotificaciones() {
        return notificacionrepository.findAll().stream()
                .map(notificacion -> modelMapper.map(notificacion, NotificacionDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (notificacionrepository.existsById(id)) {
            notificacionrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el ID: " + id);
        }
    }
}
