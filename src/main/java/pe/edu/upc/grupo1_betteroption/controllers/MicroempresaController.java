package pe.edu.upc.grupo1_betteroption.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.MicroempresaDto;
import pe.edu.upc.grupo1_betteroption.services.MicroempresaService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization")

@RequestMapping("/microempresas")
public class MicroempresaController {
    @Autowired
    private MicroempresaService microempresaservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole( 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<MicroempresaDto> microempresa(@RequestBody MicroempresaDto microempresadto) {
        return ResponseEntity.ok(microempresaservice.grabarMicroempresa(microempresadto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<MicroempresaDto>> getMicroempresas() {
        return ResponseEntity.ok(microempresaservice.getMicroempresas());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        microempresaservice.eliminar(id);
        return ResponseEntity.ok("Microempresa eliminada correctamente");
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<MicroempresaDto> actualizar(@RequestBody MicroempresaDto microempresa) {
        MicroempresaDto actualizado = microempresaservice.actualizar(microempresa);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<MicroempresaDto> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(microempresaservice.obtenerPorId(id));
    }

}
