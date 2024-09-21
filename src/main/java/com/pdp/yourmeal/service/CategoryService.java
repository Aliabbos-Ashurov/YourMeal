package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.Category;
import com.pdp.yourmeal.repository.CategoryRepository;
import com.pdp.yourmeal.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:20
 **/
@Service
@RequiredArgsConstructor
public class CategoryService implements BaseService<Category, Long> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
