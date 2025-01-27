package odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
