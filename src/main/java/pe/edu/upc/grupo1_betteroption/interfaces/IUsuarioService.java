package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
    public UsuarioDto grabarUsuario(UsuarioDto usuariodto);

    public List<UsuarioDto> getUsuarios();
}
