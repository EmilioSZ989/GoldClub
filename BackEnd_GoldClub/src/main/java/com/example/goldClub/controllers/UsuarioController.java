package com.example.goldClub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.goldClub.models.Usuario;
import com.example.goldClub.models.Empleado;
import com.example.goldClub.security.JwtService;
import com.example.goldClub.service.UsuarioService;
import com.example.goldClub.service.EmpleadoService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        // Validar si el codigoEmpleado existe en la base de datos
        Optional<Empleado> empleadoExistente = empleadoService.buscarPorCodigoEmpleado(usuario.getCodigoEmpleado());
        if (empleadoExistente.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Si no existe, retorna un error 400
        }

        // Si el código de empleado existe, registra el usuario
        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && passwordEncoder.matches(usuario.getPassword(), usuarioExistente.get().getPassword())) {
            String token = jwtService.generateToken(usuarioExistente.get());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
