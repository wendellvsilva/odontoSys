package odonto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.model.Tratamento;
import odonto.repository.TratamentoRepository;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    public Tratamento cadastrarTratamento(Tratamento tratamento) {
        return tratamentoRepository.save(tratamento);
    }

    public List<Tratamento> listarTratamentos() {
        return tratamentoRepository.findAll();
    }

    public Tratamento editarTratamento(Long id, Tratamento tratamento) {
        if (!tratamentoRepository.existsById(id)) {
            throw new RuntimeException("Tratamento não encontrado com ID: " + id);
        }
        tratamento.setId(id);
        return tratamentoRepository.save(tratamento);
    }

    public void excluirTratamento(Long id) {
        if (!tratamentoRepository.existsById(id)) {
            throw new RuntimeException("Tratamento não encontrado com ID: " + id);
        }
        tratamentoRepository.deleteById(id);
    }

    public Tratamento buscarTratamentoPorId(Long id) {
        return tratamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado com ID: " + id));
    }
}
