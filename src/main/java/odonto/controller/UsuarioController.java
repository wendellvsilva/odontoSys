package odonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import odonto.model.Usuario;
import odonto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.validarLogin(usuario.getEmail(), usuario.getSenha());

        if (autenticado) {
            return ResponseEntity.ok().body("{\"message\": \"Login realizado com sucesso\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciais inválidas\"}");
        }
    }

    @GetMapping("/{id}")
    public Usuario obterUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obterUsuario(id);
    }

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
