package pe.edu.upc.grupo1_betteroption.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id_usuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "rol", nullable = false, length = 100)
    private String rol;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;
}
