package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.CategoriaDto;
import pe.edu.upc.grupo1_betteroption.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaservice;

    @PostMapping("/registrar")
    public ResponseEntity<CategoriaDto> categoria(@RequestBody CategoriaDto categoriadto) {
        return ResponseEntity.ok(categoriaservice.grabarCategoria(categoriadto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDto>> getCategorias() {
        return ResponseEntity.ok(categoriaservice.getCategorias());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        categoriaservice.eliminar(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }

}
