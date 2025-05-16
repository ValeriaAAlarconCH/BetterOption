package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductoDto> producto(@RequestBody ProductoDto productodto) {
        return ResponseEntity.ok(productoservice.grabarProducto(productodto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return ResponseEntity.ok(productoservice.getProductos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        productoservice.eliminar(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @GetMapping("/BuscarNombre")
    public ResponseEntity<List<ProductoDto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoservice.buscarPorNombre(nombre));
    }

//    @GetMapping("/FiltroCategoria/{idCategoria}")
//    public ResponseEntity<List<ProductoDto>> filtrarPorCategoria(@PathVariable Long idCategoria) {
//        return ResponseEntity.ok(productoservice.filtrarPorCategoria(idCategoria));
//    }

    @GetMapping("/FiltroPrecio")
    public ResponseEntity<List<ProductoDto>> filtrarPorPrecio(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productoservice.filtrarPorPrecio(min, max));
    }

//    @GetMapping("/PromocionesActivas")
//    public ResponseEntity<List<ProductoDto>> obtenerPromocionesActivas() {
//        return ResponseEntity.ok(productoservice.obtenerPromocionesActivas());
//    }
//
//    @GetMapping("/WishlistUsuario/{idUsuario}")
//    public ResponseEntity<List<ProductoDto>> obtenerWishlistUsuario(@PathVariable Long idUsuario) {
//        return ResponseEntity.ok(productoservice.obtenerWishlistUsuario(idUsuario));
//    }
//
//    @GetMapping("/ProductosDeseados")
//    public ResponseEntity<List<ProductoDeseadoDto>> obtenerProductosMasDeseados() {
//        return ResponseEntity.ok(productoservice.obtenerProductosMasDeseados());
//    }

}
