package br.furb.tcc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.furb.tcc.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Optional<Usuario> findByUsername(String username);
	
}
