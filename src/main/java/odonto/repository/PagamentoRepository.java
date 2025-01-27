package odonto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
        List<Pagamento> findByPacienteId(Long Id);
}
