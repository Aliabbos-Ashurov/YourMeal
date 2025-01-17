package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.request.CreateProductDTO;
import com.pdp.yourmeal.dto.response.ProductDTO;
import com.pdp.yourmeal.entity.Category;
import com.pdp.yourmeal.entity.Product;
import com.pdp.yourmeal.handler.exception.ResourceNotFoundException;
import com.pdp.yourmeal.mapper.ProductMapper;
import com.pdp.yourmeal.repository.ProductRepository;
import com.pdp.yourmeal.service.aws.S3Service;
import com.pdp.yourmeal.service.base.BaseDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:09
 **/
@Service
@RequiredArgsConstructor
public class ProductService implements BaseDtoService<Product, Long, ProductDTO> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final S3Service s3Service;

    @Override
    public ProductDTO save(ProductDTO dto) {
        Product product = productMapper.toProduct(dto);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public ProductDTO create(CreateProductDTO dto) {
        Category category = categoryService.findByTitleObject(dto.category());
        String imageURL = s3Service.uploadFile(dto.file());
        Product product = Product.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .calories(dto.calories())
                .category(category)
                .weight(dto.weight())
                .compound(dto.compound())
                .image(imageURL)
                .build();
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }


    @Override
    @Transactional
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found By Id: {0}", id));
        return productMapper.toProductDTO(product);
    }

    public List<ProductDTO> findAllByCategory(String title) {
        return productRepository.findAllByCategoryTitle(title).stream()
                .map(productMapper::toProductDTO)
                .toList();
    }

    public Product findByIdProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found By Id: {0}", id));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAllWithCompound().stream()
                .map(productMapper::toProductDTO)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

}
