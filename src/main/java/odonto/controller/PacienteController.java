package odonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Paciente;
import odonto.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.cadastrarPaciente(paciente);
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @PutMapping("/{id}")
    public Paciente editarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.editarPaciente(id, paciente);
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
    }
}
