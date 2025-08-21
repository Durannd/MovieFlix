package br.com.ricael.movieflix.moviefliex.controller;


import br.com.ricael.movieflix.moviefliex.controller.request.CategoryRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.CategoryResponse;
import br.com.ricael.movieflix.moviefliex.entity.Category;
import br.com.ricael.movieflix.moviefliex.mapper.CategoryMapper;
import br.com.ricael.movieflix.moviefliex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList());
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        Category newcategory = CategoryMapper.toCategory(request);
        Category savedcategory = categoryService.save(newcategory);
        CategoryResponse retorno = CategoryMapper.toCategoryResponse(savedcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse>  getCategoryById(@PathVariable Long id) {
     return categoryService.findById(id)
              .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
              .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
