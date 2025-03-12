package odonto.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import odonto.model.Consulta;
import odonto.model.Dentista;
import odonto.model.Especialidade;
import odonto.repository.ConsultaRepository;
import odonto.dto.DadosCadastroDentista;
import odonto.dto.DadosEndereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Consulta consulta;
    private Dentista dentista;

    @BeforeEach
    public void setUp() {
        assertNotNull(consultaService, "ConsultaService não foi injetado!");
        MockitoAnnotations.openMocks(this);

        DadosEndereco endereco = new DadosEndereco("Rua X", "Bairro Y", "12345-678", "Cidade Z", "UF", "Complemento", "123");
        DadosCadastroDentista dadosCadastroDentista = new DadosCadastroDentista("Dentista Teste", "12345", Especialidade.ORTODONTISTA, endereco);

        dentista = new Dentista(dadosCadastroDentista);

        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setDataHora(LocalDateTime.now().plusDays(1));
        consulta.setDentista(dentista);
    }

    @Test
    public void testAgendarConsulta() {
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        Consulta resultado = consultaService.agendarConsulta(consulta);

        assertNotNull(resultado);
        assertEquals(consulta.getId(), resultado.getId());
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    public void testReagendarConsulta_Sucesso() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        consulta.setDataHora(LocalDateTime.now().plusDays(2));

        Consulta resultado = consultaService.reagendarConsulta(1L, consulta);

        assertNotNull(resultado);
        assertEquals(consulta.getDataHora(), resultado.getDataHora());
        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    public void testReagendarConsulta_ConsultaNaoEncontrada() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.reagendarConsulta(1L, consulta);
        });

        assertEquals("Consulta não encontrada com ID: 1", exception.getMessage());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarConsultas() {
        when(consultaRepository.findAll()).thenReturn(Arrays.asList(consulta));

        var resultado = consultaService.listarConsultas();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        verify(consultaRepository, times(1)).findAll();
    }

    @Test
    public void testCancelarConsulta_Sucesso() {
        when(consultaRepository.existsById(1L)).thenReturn(true);

        consultaService.cancelarConsulta(1L);

        verify(consultaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCancelarConsulta_ConsultaNaoEncontrada() {
        when(consultaRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.cancelarConsulta(1L);
        });

        assertEquals("Consulta não encontrada com ID: 1", exception.getMessage());
        verify(consultaRepository, times(0)).deleteById(1L);
    }



    @Test
    public void testValidarDisponibilidade_SemConflito() {
        when(consultaRepository.existsByDentistaIdAndDataHoraBetween(
                anyLong(),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(false);

        assertDoesNotThrow(() -> consultaService.validarDisponibilidade(consulta));
    }
}
