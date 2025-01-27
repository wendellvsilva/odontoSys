package odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
