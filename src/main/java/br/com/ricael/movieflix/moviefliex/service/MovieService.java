package br.com.ricael.movieflix.moviefliex.service;

import br.com.ricael.movieflix.moviefliex.entity.Category;
import br.com.ricael.movieflix.moviefliex.entity.Movie;
import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import br.com.ricael.movieflix.moviefliex.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreaming(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> update (Long movieId, Movie movie){
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isPresent()){

            Movie m = optionalMovie.get();
            m.setTitle(movie.getTitle());
            m.setDescription(movie.getDescription());
            m.setReleaseDate(movie.getReleaseDate());
            m.setRating(movie.getRating());
            m.getStreamings().clear();
            m.getStreamings().addAll(findStreaming(movie.getStreamings()));
            m.getCategories().clear();
            m.getCategories().addAll(findCategories(movie.getCategories()));

            movieRepository.save(m);

            return Optional.of(m);
        }
        return Optional.empty();

    }

    public boolean delete(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent()){
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> foundCategories = new ArrayList<>();
        categories.forEach(category -> {
            categoryService
                    .findById(category.getId())
                    .ifPresent(foundCategories::add);
        });
        return foundCategories;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings){
        List<Streaming> foundStreamings = new ArrayList<>();
        streamings.forEach(streaming ->
                streamingService.findById(streaming.getId()).ifPresent(foundStreamings:: add ));
        return foundStreamings;
    }
}
