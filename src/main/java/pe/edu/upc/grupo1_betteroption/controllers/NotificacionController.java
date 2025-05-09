package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.NotificacionDto;
import pe.edu.upc.grupo1_betteroption.services.NotificacionService;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionservice;

    @PostMapping("/registrar")
    public ResponseEntity<NotificacionDto> notificacion(@RequestBody NotificacionDto notificaciondto) {
        return ResponseEntity.ok(notificacionservice.grabarNotificacion(notificaciondto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NotificacionDto>> getNotificaciones() {
        return ResponseEntity.ok(notificacionservice.getNotificaciones());
    }

}
