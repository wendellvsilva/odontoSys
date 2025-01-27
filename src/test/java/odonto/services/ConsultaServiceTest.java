package odonto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import odonto.model.Consulta;
import odonto.repository.ConsultaRepository;

class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgendarConsulta() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        consulta.setDataHora(LocalDateTime.now());

        when(consultaRepository.save(consulta)).thenReturn(consulta);
        Consulta consultaAgendada = consultaService.agendarConsulta(consulta);
        assertNotNull(consultaAgendada);
        assertEquals(1L, consultaAgendada.getId());
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void testReagendarConsulta() {
        Long consultaId = 1L;
        LocalDateTime novaDataHora = LocalDateTime.now().plusDays(1);

        Consulta consultaExistente = new Consulta();
        consultaExistente.setId(consultaId);
        consultaExistente.setDataHora(LocalDateTime.now());

        when(consultaRepository.findById(consultaId)).thenReturn(Optional.of(consultaExistente));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consultaExistente);

        Consulta novaConsulta = new Consulta();
        novaConsulta.setDataHora(novaDataHora);

        Consulta consultaReagendada = consultaService.reagendarConsulta(consultaId, novaConsulta);
        assertNotNull(consultaReagendada);
        assertEquals(novaDataHora, consultaReagendada.getDataHora());
        verify(consultaRepository, times(1)).findById(consultaId);
        verify(consultaRepository, times(1)).save(consultaExistente);
    }

    @Test
    void testCancelarConsulta() {
    
        Long consultaId = 1L;

        when(consultaRepository.existsById(consultaId)).thenReturn(true);

        consultaService.cancelarConsulta(consultaId);

        verify(consultaRepository, times(1)).existsById(consultaId);
        verify(consultaRepository, times(1)).deleteById(consultaId);
    }

    @Test
    void testCancelarConsultaInexistente() {
        Long consultaId = 8899L;

        when(consultaRepository.existsById(consultaId)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> consultaService.cancelarConsulta(consultaId));

        assertEquals("Consulta não encontrada com ID: " + consultaId, exception.getMessage());
        verify(consultaRepository, times(1)).existsById(consultaId);
        verify(consultaRepository, never()).deleteById(anyLong());
    }
}
