package odonto.controller;

import odonto.dto.DadosCadastroDentista;
import odonto.model.Dentista;
import odonto.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    public Dentista cadastrarDentista(@RequestBody DadosCadastroDentista dados) {
        return dentistaService.cadastrarDentista(dados);
    }

    @GetMapping
    public List<Dentista> listarTodosDentistas() {
        return dentistaService.listarTodosDentistas();
    }

    @GetMapping("/{id}")
    public Dentista buscarDentistaPorId(@PathVariable Long id) {
        return dentistaService.buscarDentistaPorId(id);
    }
}
