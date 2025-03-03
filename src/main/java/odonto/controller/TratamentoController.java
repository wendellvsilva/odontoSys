package odonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Tratamento;
import odonto.services.TratamentoService;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    @PostMapping
    public Tratamento cadastrarTratamento(@RequestBody Tratamento tratamento) {
        return tratamentoService.cadastrarTratamento(tratamento);
    }

    @GetMapping
    public List<Tratamento> listarTratamentos() {
        return tratamentoService.listarTratamentos();
    }

    @GetMapping("/{id}")
    public Tratamento buscarTratamentoPorId(@PathVariable Long id) {
        return tratamentoService.buscarTratamentoPorId(id);
    }

    @PutMapping("/{id}")
    public Tratamento editarTratamento(@PathVariable Long id, @RequestBody Tratamento tratamento) {
        return tratamentoService.editarTratamento(id, tratamento);
    }

    @DeleteMapping("/{id}")
    public void excluirTratamento(@PathVariable Long id) {
        tratamentoService.excluirTratamento(id);
    }
}
