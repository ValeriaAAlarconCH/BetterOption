package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.CatalogoPromocionesDto;
import pe.edu.upc.grupo1_betteroption.services.CatalogoPromocionesService;

import java.util.List;

@RestController
@RequestMapping("/catalogospromociones")
public class CatalogoPromocionesController {
    @Autowired
    private CatalogoPromocionesService catalogopromocionesservice;

    @PostMapping("/registrar")
    public ResponseEntity<CatalogoPromocionesDto> catalogopromociones(@RequestBody CatalogoPromocionesDto catalogopromocionesdto) {
        return ResponseEntity.ok(catalogopromocionesservice.grabarCatalogoPromociones(catalogopromocionesdto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CatalogoPromocionesDto>> getCatalogosPromociones() {
        return ResponseEntity.ok(catalogopromocionesservice.getCatalogosPromociones());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        catalogopromocionesservice.eliminar(id);
        return ResponseEntity.ok("CatalogoPromociones Eliminado correctamente");
    }

}
