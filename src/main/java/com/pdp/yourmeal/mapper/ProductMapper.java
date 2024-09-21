package com.pdp.yourmeal.mapper;

import com.pdp.yourmeal.dto.response.ProductDTO;
import com.pdp.yourmeal.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:21
 **/
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category",source = "category.title")
    ProductDTO toProductDTO(Product product);

    @InheritInverseConfiguration
    Product toProduct(ProductDTO productDTO);
}
