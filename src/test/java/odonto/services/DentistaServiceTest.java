package odonto.services;

import odonto.dto.DadosCadastroDentista;
import odonto.dto.DadosEndereco;
import odonto.model.Dentista;
import odonto.model.Especialidade;
import odonto.repository.DentistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DentistaServiceTest {

    @InjectMocks
    private DentistaService dentistaService;

    @Mock
    private DentistaRepository dentistaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarDentista() {
        DadosEndereco endereco = new DadosEndereco("Rua Exemplo", "Centro", "12345-678", "Cidade Exemplo", "UF", "Apto 101", "42");
        DadosCadastroDentista dados = new DadosCadastroDentista("João", "123456789", Especialidade.CLINICO_GERAL, endereco);

        Dentista dentista = new Dentista(dados);
        when(dentistaRepository.save(any(Dentista.class))).thenReturn(dentista);

        Dentista result = dentistaService.cadastrarDentista(dados);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(dentistaRepository, times(1)).save(any(Dentista.class));
    }

    @Test
    public void testListarTodosDentistas() {
        DadosEndereco endereco = new DadosEndereco("Rua Exemplo", "Centro", "12345-678", "Cidade Exemplo", "UF", "Apto 101", "42");
        Dentista dentista1 = new Dentista(new DadosCadastroDentista("João", "123456789", Especialidade.CLINICO_GERAL, endereco));
        Dentista dentista2 = new Dentista(new DadosCadastroDentista("Maria", "987654321", Especialidade.ENDODONTISTA, endereco));

        when(dentistaRepository.findAll()).thenReturn(Arrays.asList(dentista1, dentista2));

        List<Dentista> dentistas = dentistaService.listarTodosDentistas();

        assertNotNull(dentistas);
        assertEquals(2, dentistas.size());
        assertEquals("João", dentistas.get(0).getNome());
        assertEquals("Maria", dentistas.get(1).getNome());
    }

    @Test
    public void testBuscarDentistaPorId() {
        DadosEndereco endereco = new DadosEndereco("Rua Exemplo", "Centro", "12345-678", "Cidade Exemplo", "UF", "Apto 101", "42");
        Dentista dentista = new Dentista(new DadosCadastroDentista("João", "123456789", Especialidade.CLINICO_GERAL,endereco));
        when(dentistaRepository.findById(1L)).thenReturn(Optional.of(dentista));

        Dentista result = dentistaService.buscarDentistaPorId(1L);

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    public void testBuscarDentistaPorIdNaoEncontrado() {
        when(dentistaRepository.findById(1L)).thenReturn(Optional.empty());

        Dentista result = dentistaService.buscarDentistaPorId(1L);

        assertNull(result);
    }
}
