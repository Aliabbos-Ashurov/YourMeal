package com.pdp.yourmeal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:56
 **/
public record CreateProductDTO(
        @NotNull MultipartFile file,
        @NotBlank @NonNull String title,
        @NotNull double price,
        @NotNull double weight,
        @NotNull double calories,
        @NotBlank @NonNull String description,
        @NotNull List<String> compound,
        @NonNull String category
) implements DTO {
}
