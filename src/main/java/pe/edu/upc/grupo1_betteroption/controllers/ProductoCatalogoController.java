package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoCatalogoDto;
import pe.edu.upc.grupo1_betteroption.services.ProductoCatalogoService;

import java.util.List;

@RestController
@RequestMapping("/productoscatalogos")
public class ProductoCatalogoController {
    @Autowired
    private ProductoCatalogoService productocatalogoservice;

    @PostMapping("/registrar")
    public ResponseEntity<ProductoCatalogoDto> productocatalogo(@RequestBody ProductoCatalogoDto productocatalogodto) {
        return ResponseEntity.ok(productocatalogoservice.grabarProductoCatalogo(productocatalogodto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoCatalogoDto>> getProdctoCatalogos() {
        return ResponseEntity.ok(productocatalogoservice.getProductosCatalogos());
    }
}
