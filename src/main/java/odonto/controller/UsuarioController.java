package odonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Usuario;
import odonto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String email, @RequestParam String senha) {
        return usuarioService.validarLogin(email, senha);
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
