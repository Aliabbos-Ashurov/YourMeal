package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.ProductDTO;
import com.pdp.yourmeal.entity.Product;
import com.pdp.yourmeal.handler.exception.ResourceNotFoundException;
import com.pdp.yourmeal.mapper.ProductMapper;
import com.pdp.yourmeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public ProductDTO save(ProductDTO dto) {
        Product product = productMapper.toProduct(dto);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found By Id: {0}", id));
        return productMapper.toProductDTO(product);
    }

    public Product findByIdProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Not Found By Id: {0}", id));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
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
