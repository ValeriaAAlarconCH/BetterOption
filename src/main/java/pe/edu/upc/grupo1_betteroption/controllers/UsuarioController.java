package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.grupo1_betteroption.dtos.UsuarioDto;
import pe.edu.upc.grupo1_betteroption.interfaces.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioservice;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UsuarioDto> usuario(@RequestBody UsuarioDto usuariodto) {
        return ResponseEntity.ok(usuarioservice.grabarUsuario(usuariodto));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return ResponseEntity.ok(usuarioservice.getUsuarios());
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        usuarioservice.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UsuarioDto> actualizar(@PathVariable("id") Long id, @RequestBody UsuarioDto usuariodto) {
        UsuarioDto actualizar = usuarioservice.actualizar(id, usuariodto);
        return ResponseEntity.ok(actualizar);
    }

}
