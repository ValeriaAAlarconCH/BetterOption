package pe.edu.upc.grupo1_betteroption.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.NotificacionDto;
import pe.edu.upc.grupo1_betteroption.services.NotificacionService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization")

@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<NotificacionDto> notificacion(@RequestBody NotificacionDto notificaciondto) {
        return ResponseEntity.ok(notificacionservice.grabarNotificacion(notificaciondto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<NotificacionDto>> getNotificaciones() {
        return ResponseEntity.ok(notificacionservice.getNotificaciones());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        notificacionservice.eliminar(id);
        return ResponseEntity.ok("Notificación Eliminada correctamente");
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<NotificacionDto> actualizar(@RequestBody NotificacionDto notificaciondto) {
        NotificacionDto actualizado = notificacionservice.actualizar(notificaciondto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<NotificacionDto> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificacionservice.obtenerPorId(id));
    }

}
