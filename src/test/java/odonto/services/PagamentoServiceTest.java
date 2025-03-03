package odonto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import odonto.model.Pagamento;
import odonto.repository.PacienteRepository;
import odonto.repository.PagamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setPacienteId(1L);
        pagamento.setDentistaId(2L);
        pagamento.setValor(new BigDecimal("200.00"));
        pagamento.setStatus("PENDENTE");
        pagamento.setPago(false);
    }

    @Test
    void deveRegistrarPagamentoComSucesso() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        Pagamento resultado = pagamentoService.registrarPagamento(pagamento);

        assertNotNull(resultado);
        assertEquals("PAGO", resultado.getStatus());
        assertNotNull(resultado.getDataPagamento());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    void deveLancarExcecaoQuandoValorPagamentoInvalido() {
        pagamento.setValor(BigDecimal.ZERO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                pagamentoService.registrarPagamento(pagamento)
        );

        assertEquals("O valor do pagamento deve ser maior que zero.", exception.getMessage());
        verify(pacienteRepository, never()).existsById(anyLong());
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                pagamentoService.registrarPagamento(pagamento)
        );

        assertEquals("Paciente não encontrado com ID: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void deveLancarExcecaoQuandoDentistaNaoExiste() {
        pagamento.setDentistaId(null);
        when(pacienteRepository.existsById(1L)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                pagamentoService.registrarPagamento(pagamento)
        );

        assertEquals("Dentista não encontrado com ID: null", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void deveRegistrarPendencia() {
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        Pagamento resultado = pagamentoService.registrarPendencia(pagamento);

        assertNotNull(resultado);
        assertEquals("PENDENTE", resultado.getStatus());
        assertFalse(resultado.getPago());
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    void deveQuitarPendenciaComSucesso() {
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(pagamento));
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        Pagamento resultado = pagamentoService.quitarPendencia(1L);

        assertEquals("QUITADO", resultado.getStatus());
        assertTrue(resultado.getPago());
        assertNotNull(resultado.getDataPagamento());
        verify(pagamentoRepository, times(1)).findById(1L);
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    void deveLancarExcecaoQuandoPagamentoNaoExisteAoQuitar() {
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                pagamentoService.quitarPendencia(1L)
        );

        assertEquals("Pagamento não encontrado com ID: 1", exception.getMessage());
        verify(pagamentoRepository, times(1)).findById(1L);
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void deveListarPagamentosPorPaciente() {
        List<Pagamento> listaPagamentos = Arrays.asList(pagamento);
        when(pagamentoRepository.findByPacienteId(1L)).thenReturn(listaPagamentos);

        List<Pagamento> resultado = pagamentoService.listarPagamentosPorPaciente(1L);

        assertEquals(1, resultado.size());
        assertEquals("PENDENTE", resultado.get(0).getStatus());
        verify(pagamentoRepository, times(1)).findByPacienteId(1L);
    }
}
