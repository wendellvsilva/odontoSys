package odonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import odonto.model.Paciente;
import odonto.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @PutMapping("/{id}")
    //verificar como diminuir essa bosta
    public Paciente editarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteExistente = pacienteRepository.findById(id).orElseThrow();
        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setCpf(paciente.getCpf());
        pacienteExistente.setTelefone(paciente.getTelefone());
        pacienteExistente.setEndereco(paciente.getEndereco());
        pacienteExistente.setHistoricoMedico(paciente.getHistoricoMedico());
        return pacienteRepository.save(pacienteExistente);
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}