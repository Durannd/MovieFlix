package br.com.ricael.movieflix.moviefliex.mapper;

import br.com.ricael.movieflix.moviefliex.controller.request.CategoryRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.CategoryResponse;
import br.com.ricael.movieflix.moviefliex.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();

    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
