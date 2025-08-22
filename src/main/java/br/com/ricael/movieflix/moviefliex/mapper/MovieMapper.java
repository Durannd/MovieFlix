package br.com.ricael.movieflix.moviefliex.mapper;

import br.com.ricael.movieflix.moviefliex.controller.request.MovieRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.CategoryResponse;
import br.com.ricael.movieflix.moviefliex.controller.response.MovieResponse;
import br.com.ricael.movieflix.moviefliex.controller.response.StreamingResponse;
import br.com.ricael.movieflix.moviefliex.entity.Category;
import br.com.ricael.movieflix.moviefliex.entity.Movie;
import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
    public static Movie toMovie(MovieRequest request) {

        List<Category> listCat = request
                .categories()
                .stream()
                .map(catId -> Category.builder().id(catId).build())
                .toList();

        List<Streaming> listSt = request
                .streamings()
                .stream()
                .map(stId -> Streaming.builder().id(stId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .rating(request.rating())
                .releaseDate(request.releaseDate())
                .categories(listCat)
                .streamings(listSt)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> category = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streaming = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(category)
                .streamings(streaming)
                .build();
    }
}
