package odonto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import odonto.model.Usuario;
import odonto.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("senhaSegura123");
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveObterUsuarioPorIdExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obterUsuario(1L);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoObterUsuarioInexistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                usuarioService.obterUsuario(1L)
        );

        assertEquals("Usuário não encontrado com ID: 1", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveListarTodosOsUsuarios() {
        List<Usuario> listaUsuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deveAtualizarUsuarioExistente() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Carlos Pereira");
        usuarioAtualizado.setEmail("carlos@email.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        Usuario resultado = usuarioService.atualizarUsuario(1L, usuarioAtualizado);

        assertEquals("Carlos Pereira", resultado.getNome());
        assertEquals("carlos@email.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveLancarExcecaoAoAtualizarUsuarioInexistente() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Carlos Pereira");
        usuarioAtualizado.setEmail("carlos@email.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                usuarioService.atualizarUsuario(1L, usuarioAtualizado)
        );

        assertEquals("Usuário não encontrado com ID: 1", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveAlterarSenhaComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        assertDoesNotThrow(() -> usuarioService.alterarSenha(1L, "novaSenhaSegura"));

        assertEquals("novaSenhaSegura", usuario.getSenha());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveLancarExcecaoAoAlterarSenhaDeUsuarioInexistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                usuarioService.alterarSenha(1L, "novaSenhaSegura")
        );

        assertEquals("Usuário não encontrado com ID: 1", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoAoAlterarSenhaComMenosDeOitoCaracteres() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                usuarioService.alterarSenha(1L, "12345")
        );

        assertEquals("A senha deve ter pelo menos 8 caracteres.", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
