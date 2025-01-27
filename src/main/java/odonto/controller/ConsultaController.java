package odonto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Consulta;
import odonto.repository.ConsultaRepository;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    public Consulta agendarConsulta(@RequestBody Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @PutMapping("/{id}")
    public Consulta reagendarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        Consulta consultaExistente = consultaRepository.findById(id).orElseThrow();
        consultaExistente.setDataHora(consulta.getDataHora());
        return consultaRepository.save(consultaExistente);
    }

    @DeleteMapping("/{id}")
    public void cancelarConsulta(@PathVariable Long id) {
        consultaRepository.deleteById(id);
    }
}

