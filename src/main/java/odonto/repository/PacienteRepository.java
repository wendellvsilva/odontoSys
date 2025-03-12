package odonto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import odonto.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método para encontrar pacientes pelo CPF
    List<Paciente> findByCpf(String cpf);

    // Método para encontrar pacientes pelo Nome
    List<Paciente> findByNome(String nome);
}
