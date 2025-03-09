package odonto.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import odonto.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
