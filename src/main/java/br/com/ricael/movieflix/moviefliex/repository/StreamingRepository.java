package br.com.ricael.movieflix.moviefliex.repository;

import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
