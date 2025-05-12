package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.NotificacionDto;

import java.util.List;

public interface INotificacionService {
    public NotificacionDto grabarNotificacion(NotificacionDto notificaciondto);

    public List<NotificacionDto> getNotificaciones();

    void eliminar(Long id);

}
