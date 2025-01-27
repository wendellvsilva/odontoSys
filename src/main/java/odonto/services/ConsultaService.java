package odonto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.model.Consulta;
import odonto.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta agendarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta reagendarConsulta(Long id, Consulta consulta) {
        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
        consultaExistente.setDataHora(consulta.getDataHora());
        return consultaRepository.save(consultaExistente);
    }

    public void cancelarConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com ID: " + id);
        }
        consultaRepository.deleteById(id);
    }
}
