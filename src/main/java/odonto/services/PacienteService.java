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

    public Paciente editarPaciente(Long id, Paciente paciente) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setCpf(paciente.getCpf());
        pacienteExistente.setTelefone(paciente.getTelefone());
        pacienteExistente.setEndereco(paciente.getEndereco());
        pacienteExistente.setHistoricoMedico(paciente.getHistoricoMedico());
        return pacienteRepository.save(pacienteExistente);
    }

    public void excluirPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
