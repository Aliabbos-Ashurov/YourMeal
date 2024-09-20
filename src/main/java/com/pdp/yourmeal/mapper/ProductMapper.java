package com.pdp.yourmeal.mapper;

import com.pdp.yourmeal.dto.ProductDTO;
import com.pdp.yourmeal.entity.Product;
import org.mapstruct.Mapper;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:21
 **/
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);
}
