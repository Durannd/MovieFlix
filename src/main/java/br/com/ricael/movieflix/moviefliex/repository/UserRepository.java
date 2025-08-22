package br.com.ricael.movieflix.moviefliex.repository;

import br.com.ricael.movieflix.moviefliex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
