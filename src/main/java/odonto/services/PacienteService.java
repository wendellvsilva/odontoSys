package odonto.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.model.Paciente;
import odonto.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> listarPacientesPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public List<Paciente> listarPacientesPorNome(String nome) {
        return pacienteRepository.findByCpf(nome);
    }


    public Paciente editarPaciente(String cpf, Paciente paciente) {
        // Buscar a lista de pacientes com o CPF fornecido
        List<Paciente> pacientesExistentes = pacienteRepository.findByCpf(cpf);
    
        if (pacientesExistentes.isEmpty()) {
            throw new RuntimeException("Paciente não encontrado com CPF: " + cpf);
        }
    
        // Se houver mais de um paciente com o mesmo CPF, isso pode ser tratado conforme necessário
        Paciente pacienteExistente = pacientesExistentes.get(0); // Pegando o primeiro paciente encontrado
    
        // Atualiza os dados do paciente se o campo não for nulo
        if (paciente.getNome() != null) {
            pacienteExistente.setNome(paciente.getNome());
        }
        if (paciente.getCpf() != null) {
            pacienteExistente.setCpf(paciente.getCpf());
        }
        if (paciente.getTelefone() != null) {
            pacienteExistente.setTelefone(paciente.getTelefone());
        }
        if (paciente.getEndereco() != null) {
            pacienteExistente.setEndereco(paciente.getEndereco());
        }
        if (paciente.getHistoricoMedico() != null) {
            pacienteExistente.setHistoricoMedico(paciente.getHistoricoMedico());
        }
    
        // Salva o paciente atualizado
        return pacienteRepository.save(pacienteExistente);
    }
    
    public void excluirPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
