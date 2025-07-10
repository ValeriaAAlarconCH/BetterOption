package pe.edu.upc.grupo1_betteroption.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.services.ProductoCatalogoService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization")

@RequestMapping("/productoscatalogos")
public class ProductoCatalogoController {
    @Autowired
    private ProductoCatalogoService productocatalogoservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<ProductoCatalogoDto> productocatalogo(@RequestBody ProductoCatalogoDto productocatalogodto) {
        return ResponseEntity.ok(productocatalogoservice.grabarProductoCatalogo(productocatalogodto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<ProductoCatalogoDto>> getProdctoCatalogos() {
        return ResponseEntity.ok(productocatalogoservice.getProductosCatalogos());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        productocatalogoservice.eliminar(id);
        return ResponseEntity.ok("ProductoCatalogo Eliminado correctamente");
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<ProductoCatalogoDto> actualizar(@RequestBody ProductoCatalogoDto productocatalogodto) {
        ProductoCatalogoDto actualizado = productocatalogoservice.actualizar(productocatalogodto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/promocionesactivas")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<List<ProductoDto>> promocionesActivas() {
        return ResponseEntity.ok(productocatalogoservice.obtenerProductosConPromocionesActivas());
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MICROEMPRESARIO')")
    public ResponseEntity<ProductoCatalogoDto> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productocatalogoservice.obtenerPorId(id));
    }

}
