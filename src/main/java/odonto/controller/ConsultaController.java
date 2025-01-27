package odonto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Consulta;
import odonto.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public Consulta agendarConsulta(@RequestBody Consulta consulta) {
        return consultaService.agendarConsulta(consulta);
    }

    @PutMapping("/{id}")
    public Consulta reagendarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        return consultaService.reagendarConsulta(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
    }
}
