package odonto.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.model.Pagamento;
import odonto.repository.PacienteRepository;
import odonto.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Pagamento registrarPagamento(Pagamento pagamento) {
        if (pagamento.getValor() == null || pagamento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        if (!pacienteRepository.existsById(pagamento.getPacienteId())) {
            throw new RuntimeException("Paciente não encontrado com ID: " + pagamento.getPacienteId());
        }
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento registrarPendencia(Pagamento pagamento) {
        pagamento.setStatus("PENDENTE");
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento quitarPendencia(Long id) {
    Pagamento pagamento = pagamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + id));
    pagamento.setStatus("QUITADO");
    pagamento.setDataPagamento(LocalDate.now());
    return pagamentoRepository.save(pagamento);

    }
    public List<Pagamento> listarPagamentosPorPaciente(Long pacienteId) {
        return pagamentoRepository.findByPacienteId(pacienteId);
    }


}
