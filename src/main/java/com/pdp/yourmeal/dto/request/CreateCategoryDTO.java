package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  15:49
 **/
public record CreateCategoryDTO(
        @NonNull MultipartFile icon,
        @NotBlank @NonNull String title
) implements DTO {
}
