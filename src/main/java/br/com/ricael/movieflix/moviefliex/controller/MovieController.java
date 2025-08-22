package br.com.ricael.movieflix.moviefliex.controller;

import br.com.ricael.movieflix.moviefliex.controller.request.MovieRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.MovieResponse;
import br.com.ricael.movieflix.moviefliex.entity.Movie;
import br.com.ricael.movieflix.moviefliex.mapper.MovieMapper;
import br.com.ricael.movieflix.moviefliex.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest){
       return ResponseEntity.ok( MovieMapper.toMovieResponse
               (movieService.save
                       (MovieMapper.toMovie
                               (movieRequest)))) ;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        return movie.map(value -> ResponseEntity.ok(MovieMapper.toMovieResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id,@RequestBody MovieRequest request){
      return movieService.update(id, MovieMapper.toMovie(request))
              .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
              .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id){
        if(movieService.delete(id)){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Filme deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FIlme não encontrado");
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
       return ResponseEntity.ok(movieService.findByCategory(category)
               .stream()
               .map(MovieMapper::toMovieResponse)
               .toList());
    }

}
