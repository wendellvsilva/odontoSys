package odonto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNomeContainingOrCpf(String nome, String cpf);
}