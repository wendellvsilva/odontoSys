package odonto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import odonto.model.Pagamento;
import odonto.services.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public Pagamento registrarPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.registrarPagamento(pagamento);
    }
}
