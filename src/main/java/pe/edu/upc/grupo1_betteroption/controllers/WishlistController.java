package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDeseadoDto;
import pe.edu.upc.grupo1_betteroption.dtos.ProductoDto;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;
import pe.edu.upc.grupo1_betteroption.services.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<WishlistDto> wishlist(@RequestBody WishlistDto wishlistdto) {
        return ResponseEntity.ok(wishlistservice.grabarWishlist(wishlistdto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<WishlistDto>> getWishlists() {
        return ResponseEntity.ok(wishlistservice.getWishlists());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        wishlistservice.eliminar(id);
        return ResponseEntity.ok("Wishlist eliminado correctamente");
    }

    @GetMapping("/wishlistusuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDto>> wishlistUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(wishlistservice.obtenerWishlistPorUsuario(idUsuario));
    }

    @GetMapping("/productosdeseados")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ProductoDeseadoDto>> productosMasDeseados() {
        return ResponseEntity.ok(wishlistservice.obtenerProductosMasDeseados());
    }

}
