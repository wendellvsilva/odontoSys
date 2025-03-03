package odonto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import odonto.model.Tratamento;
import odonto.repository.TratamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TratamentoServiceTest {

    @Mock
    private TratamentoRepository tratamentoRepository;

    @InjectMocks
    private TratamentoService tratamentoService;

    private Tratamento tratamento;

    @BeforeEach
    void setUp() {
        tratamento = new Tratamento();
        tratamento.setId(1L);
        tratamento.setDescricao("Clareamento Dental");
        tratamento.setDescricao("Procedimento para clareamento dos dentes");
    }

    @Test
    void deveCadastrarTratamentoComSucesso() {
        when(tratamentoRepository.save(any(Tratamento.class))).thenReturn(tratamento);

        Tratamento resultado = tratamentoService.cadastrarTratamento(tratamento);

        assertNotNull(resultado);
        assertEquals("Clareamento Dental", resultado.getDescricao());
        verify(tratamentoRepository, times(1)).save(tratamento);
    }

    @Test
    void deveListarTodosOsTratamentos() {
        List<Tratamento> listaTratamentos = Arrays.asList(tratamento);
        when(tratamentoRepository.findAll()).thenReturn(listaTratamentos);

        List<Tratamento> resultado = tratamentoService.listarTratamentos();

        assertEquals(1, resultado.size());
        assertEquals("Clareamento Dental", resultado.get(0).getDescricao());
        verify(tratamentoRepository, times(1)).findAll();
    }

    @Test
    void deveEditarTratamentoExistente() {
        Tratamento novoTratamento = new Tratamento();
        novoTratamento.setDescricao("Implante Dentário");
        novoTratamento.setDescricao("Procedimento de substituição de dentes perdidos");

        when(tratamentoRepository.existsById(1L)).thenReturn(true);
        when(tratamentoRepository.save(any(Tratamento.class))).thenReturn(novoTratamento);

        Tratamento resultado = tratamentoService.editarTratamento(1L, novoTratamento);

        assertEquals(1L, resultado.getId());
        assertEquals("Implante Dentário", resultado.getDescricao());
        verify(tratamentoRepository, times(1)).existsById(1L);
        verify(tratamentoRepository, times(1)).save(novoTratamento);
    }

    @Test
    void deveLancarExcecaoAoEditarTratamentoInexistente() {
        when(tratamentoRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                tratamentoService.editarTratamento(1L, tratamento)
        );

        assertEquals("Tratamento não encontrado com ID: 1", exception.getMessage());
        verify(tratamentoRepository, times(1)).existsById(1L);
        verify(tratamentoRepository, never()).save(any(Tratamento.class));
    }

    @Test
    void deveExcluirTratamentoExistente() {
        when(tratamentoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tratamentoRepository).deleteById(1L);

        assertDoesNotThrow(() -> tratamentoService.excluirTratamento(1L));

        verify(tratamentoRepository, times(1)).existsById(1L);
        verify(tratamentoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirTratamentoInexistente() {
        when(tratamentoRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                tratamentoService.excluirTratamento(1L)
        );

        assertEquals("Tratamento não encontrado com ID: 1", exception.getMessage());
        verify(tratamentoRepository, times(1)).existsById(1L);
        verify(tratamentoRepository, never()).deleteById(anyLong());
    }

    @Test
    void deveBuscarTratamentoPorIdExistente() {
        when(tratamentoRepository.findById(1L)).thenReturn(Optional.of(tratamento));

        Tratamento resultado = tratamentoService.buscarTratamentoPorId(1L);

        assertNotNull(resultado);
        assertEquals("Clareamento Dental", resultado.getDescricao());
        verify(tratamentoRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarTratamentoInexistente() {
        when(tratamentoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                tratamentoService.buscarTratamentoPorId(1L)
        );

        assertEquals("Tratamento não encontrado com ID: 1", exception.getMessage());
        verify(tratamentoRepository, times(1)).findById(1L);
    }
}
