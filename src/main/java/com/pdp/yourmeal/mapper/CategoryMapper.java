package com.pdp.yourmeal.mapper;

import com.pdp.yourmeal.dto.response.CategoryDTO;
import com.pdp.yourmeal.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:09
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(Category category);

    @InheritInverseConfiguration
    Category toCategory(CategoryDTO categoryDTO);
}
