 package odonto.services;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.*;

 import java.util.Arrays;
 import java.util.List;
 import java.util.Optional;

 import odonto.model.Paciente;
 import odonto.repository.PacienteRepository;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.junit.jupiter.MockitoExtension;

 @ExtendWith(MockitoExtension.class)
 class PacienteServiceTest {

     @Mock
     private PacienteRepository pacienteRepository;

     @InjectMocks
     private PacienteService pacienteService;

     private Paciente paciente;

     @BeforeEach
     void setUp() {
         paciente = new Paciente();
         paciente.setId(1L);
         paciente.setNome("João Silva");
         paciente.setCpf("123.456.789-00");
         paciente.setTelefone("99999-9999");
         paciente.setEndereco("Rua Exemplo, 123");
         paciente.setHistoricoMedico("Sem alergias");
     }

     @Test
     void deveCadastrarPaciente() {
         when(pacienteRepository.save(paciente)).thenReturn(paciente);

         Paciente resultado = pacienteService.cadastrarPaciente(paciente);

         assertNotNull(resultado);
         assertEquals("João Silva", resultado.getNome());
         verify(pacienteRepository, times(1)).save(paciente);
     }

     @Test
     void deveListarPacientes() {
         List<Paciente> listaPacientes = Arrays.asList(paciente);
         when(pacienteRepository.findAll()).thenReturn(listaPacientes);

         List<Paciente> resultado = pacienteService.listarPacientes();

         assertEquals(1, resultado.size());
         assertEquals("João Silva", resultado.get(0).getNome());
         verify(pacienteRepository, times(1)).findAll();
     }

//     @Test
//     void deveEditarPaciente() {
//         Paciente pacienteAtualizado = new Paciente();
//         pacienteAtualizado.setNome("Carlos Souza");
//         pacienteAtualizado.setTelefone("88888-8888");
//
//         when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
//         when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);
//
//         Paciente resultado = pacienteService.editarPaciente(1L, pacienteAtualizado);
//
//         assertEquals("Carlos Souza", resultado.getNome());
//         assertEquals("88888-8888", resultado.getTelefone());
//         verify(pacienteRepository, times(1)).findById(1L);
//         verify(pacienteRepository, times(1)).save(paciente);
//     }

     @Test
     void deveExcluirPaciente() {
         when(pacienteRepository.existsById(1L)).thenReturn(true);
         doNothing().when(pacienteRepository).deleteById(1L);

         assertDoesNotThrow(() -> pacienteService.excluirPaciente(1L));

         verify(pacienteRepository, times(1)).existsById(1L);
         verify(pacienteRepository, times(1)).deleteById(1L);
     }

     @Test
     void deveLancarExcecaoAoExcluirPacienteInexistente() {
         when(pacienteRepository.existsById(1L)).thenReturn(false);

         RuntimeException exception = assertThrows(RuntimeException.class, () -> pacienteService.excluirPaciente(1L));

         assertEquals("Paciente não encontrado com ID: 1", exception.getMessage());
         verify(pacienteRepository, times(1)).existsById(1L);
         verify(pacienteRepository, never()).deleteById(anyLong());
     }
 }
