package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<ProductoDto> producto(@RequestBody ProductoDto productodto) {
        return ResponseEntity.ok(productoservice.grabarProducto(productodto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return ResponseEntity.ok(productoservice.getProductos());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole( 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        productoservice.eliminar(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @GetMapping("/buscarnombre/{nombre}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<ProductoDto>> buscarPorNombre(@PathVariable("nombre") String nombre) {
        return ResponseEntity.ok(productoservice.buscarPorNombre(nombre));
    }

//    @GetMapping("/FiltroCategoria/{idCategoria}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
//    public ResponseEntity<List<ProductoDto>> filtrarPorCategoria(@PathVariable Long idCategoria) {
//        return ResponseEntity.ok(productoservice.filtrarPorCategoria(idCategoria));
//    }

    @GetMapping("/filtroprecio/{min}/{max}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<ProductoDto>> filtrarPorPrecio(@PathVariable("min") Double min, @PathVariable("max") Double max) {
        return ResponseEntity.ok(productoservice.filtrarPorPrecio(min, max));
    }


}
