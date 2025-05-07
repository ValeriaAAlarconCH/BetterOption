package pe.edu.upc.grupo1_betteroption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsuarioDto> usuario(@RequestBody UsuarioDto usuariodto) {
        return ResponseEntity.ok(usuarioservice.grabarUsuario(usuariodto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return ResponseEntity.ok(usuarioservice.getUsuarios());
    }

}
