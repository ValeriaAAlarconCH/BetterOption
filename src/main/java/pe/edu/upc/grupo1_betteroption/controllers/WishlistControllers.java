package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;
import pe.edu.upc.grupo1_betteroption.services.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistControllers {
    @Autowired
    private WishlistService wishlistservice;

    @PostMapping("/registrar")
    public ResponseEntity<WishlistDto> wishlist(@RequestBody WishlistDto wishlistdto) {
        return ResponseEntity.ok(wishlistservice.grabarWishlist(wishlistdto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<WishlistDto>> getWishlists() {
        return ResponseEntity.ok(wishlistservice.getWishlists());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        wishlistservice.eliminar(id);
        return ResponseEntity.ok("Wishlist eliminado correctamente");
    }


}
