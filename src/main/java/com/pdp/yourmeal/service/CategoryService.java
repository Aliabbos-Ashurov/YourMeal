package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.request.CreateCategoryDTO;
import com.pdp.yourmeal.dto.response.CategoryDTO;
import com.pdp.yourmeal.entity.Category;
import com.pdp.yourmeal.handler.exception.CategoryNotFoundException;
import com.pdp.yourmeal.mapper.CategoryMapper;
import com.pdp.yourmeal.repository.CategoryRepository;
import com.pdp.yourmeal.service.aws.S3Service;
import com.pdp.yourmeal.service.base.BaseDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:20
 **/
@Service
@RequiredArgsConstructor
public class CategoryService implements BaseDtoService<Category, Long, CategoryDTO> {

    private final S3Service s3Service;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryDTO findByTitle(String title) {
        Category category = categoryRepository.findByTitleContainsIgnoreCase(title);
        return categoryMapper.toCategoryDTO(category);
    }


    public Category findByTitleObject(String title) {
        return categoryRepository.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        Category category = categoryRepository.save(categoryMapper.toCategory(dto));
        return categoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO create(CreateCategoryDTO categoryDTO) {
        String imageURL = s3Service.uploadFile(categoryDTO.icon());
        return save(new CategoryDTO(imageURL, categoryDTO.title()));
    }

    @Override
    public CategoryDTO findById(Long aLong) {
        Category category = categoryRepository.findById(aLong)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: {0}", aLong));
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(categoryMapper::toCategoryDTO)
                .toList();
    }

    @Override
    public void deleteById(Long aLong) {
        categoryRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return categoryRepository.existsById(aLong);
    }

}
