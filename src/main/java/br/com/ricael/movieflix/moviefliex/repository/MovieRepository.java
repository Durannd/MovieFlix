package br.com.ricael.movieflix.moviefliex.repository;

import br.com.ricael.movieflix.moviefliex.entity.Category;
import br.com.ricael.movieflix.moviefliex.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);

}
