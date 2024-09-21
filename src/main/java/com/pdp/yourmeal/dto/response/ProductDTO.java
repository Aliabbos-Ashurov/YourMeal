package com.pdp.yourmeal.dto.response;

import com.pdp.yourmeal.dto.DTO;
import com.pdp.yourmeal.entity.Category;
import lombok.NonNull;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:12
 **/
public record ProductDTO(
        @NonNull Long id,
        @NonNull String image,
        @NonNull String title,
        double price,
        double weight,
        double calories,
        @NonNull String description,
        @NonNull List<String> compound,
        @NonNull String category
) implements DTO {
}
