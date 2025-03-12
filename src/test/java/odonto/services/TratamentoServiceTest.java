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
        // Aqui você define o valor correto para o tratamento a ser usado no teste
        tratamento = new Tratamento();
        tratamento.setId(1L);
        tratamento.setDescricao("Clareamento Dental"); // Definir uma descrição específica
    }
    
    @Test
    void deveCadastrarTratamentoComSucesso() {
        // Agora, o valor de descricao será "Clareamento Dental"
        when(tratamentoRepository.save(any(Tratamento.class))).thenReturn(tratamento);
    
        Tratamento resultado = tratamentoService.cadastrarTratamento(tratamento);
    
        assertNotNull(resultado);
        assertEquals("Clareamento Dental", resultado.getDescricao()); // Verifique se a descrição está correta
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
    
    // @Test
    // void deveEditarTratamentoExistente() {
    //     Tratamento novoTratamento = new Tratamento();
    //     novoTratamento.setDescricao("Procedimento de substituição de dentes perdidos"); // Descrição correta para este teste
    //     novoTratamento.setDescricao("Procedimento de substituição de dentes perdidos");
    
    //     when(tratamentoRepository.existsById(1L)).thenReturn(true);
    //     when(tratamentoRepository.save(any(Tratamento.class))).thenReturn(novoTratamento);
    
    //     Tratamento resultado = tratamentoService.editarTratamento(1L, novoTratamento);
    
    //     assertEquals(1L, resultado.getId());
    //     assertEquals("Implante Dentário", resultado.getDescricao()); // Verifique se a descrição está correta
    //     verify(tratamentoRepository, times(1)).existsById(1L);
    //     verify(tratamentoRepository, times(1)).save(novoTratamento);
    // }
}    
