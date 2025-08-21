package br.com.ricael.movieflix.moviefliex.service;

import br.com.ricael.movieflix.moviefliex.controller.request.CategoryRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.CategoryResponse;
import br.com.ricael.movieflix.moviefliex.entity.Category;
import br.com.ricael.movieflix.moviefliex.mapper.CategoryMapper;
import br.com.ricael.movieflix.moviefliex.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }


    public Category save( Category request){
       categoryRepository.save(request);
        return request;
    }
    
    public Optional<Category> findById (Long id){

        return categoryRepository.findById(id);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
