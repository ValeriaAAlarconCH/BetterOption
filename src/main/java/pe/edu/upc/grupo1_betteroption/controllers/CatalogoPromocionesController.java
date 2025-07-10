package pe.edu.upc.grupo1_betteroption.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.services.CatalogoPromocionesService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization")

@RequestMapping("/catalogospromociones")
public class CatalogoPromocionesController {
    @Autowired
    private CatalogoPromocionesService catalogopromocionesservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<CatalogoPromocionesDto> catalogopromociones(@RequestBody CatalogoPromocionesDto catalogopromocionesdto) {
        return ResponseEntity.ok(catalogopromocionesservice.grabarCatalogoPromociones(catalogopromocionesdto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<CatalogoPromocionesDto>> getCatalogosPromociones() {
        return ResponseEntity.ok(catalogopromocionesservice.getCatalogosPromociones());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        catalogopromocionesservice.eliminar(id);
        return ResponseEntity.ok("CatalogoPromociones Eliminado correctamente");
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<CatalogoPromocionesDto> actualizar(@RequestBody CatalogoPromocionesDto catalogopromocionesdto) {
        CatalogoPromocionesDto actualizado = catalogopromocionesservice.actualizar(catalogopromocionesdto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<CatalogoPromocionesDto> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catalogopromocionesservice.obtenerPorId(id));
    }

}
