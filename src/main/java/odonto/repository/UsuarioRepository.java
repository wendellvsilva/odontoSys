package odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
