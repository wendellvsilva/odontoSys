package odonto.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.model.Consulta;
import odonto.repository.ConsultaRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void cancelarConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com ID: " + id);
        }
        consultaRepository.deleteById(id);
    }

    private void validarDisponibilidade(Consulta consulta) {
        LocalDateTime inicioConsulta = consulta.getDataHora();
        LocalDateTime fimConsulta = inicioConsulta.plusHours(1); // Define a duração mínima

        boolean existeConflito = consultaRepository.existsByDentistaIdAndDataHoraBetween(
                (Long) consulta.getDentista().getId(),
                inicioConsulta,
                fimConsulta
        );

        if (existeConflito) {
            throw new RuntimeException("Horário indisponível. Já existe uma consulta agendada para esse período.");
        }
    }

}
