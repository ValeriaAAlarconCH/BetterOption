package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoservice;

    @PostMapping("/registrar")
    public ResponseEntity<ProductoDto> producto(@RequestBody ProductoDto productodto) {
        return ResponseEntity.ok(productoservice.grabarProducto(productodto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return ResponseEntity.ok(productoservice.getProductos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
        productoservice.eliminarProducto(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

}
