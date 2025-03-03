package odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Consulta;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByDentistaIdAndDataHoraBetween(Long dentistaId, LocalDateTime inicio, LocalDateTime fim);
}
