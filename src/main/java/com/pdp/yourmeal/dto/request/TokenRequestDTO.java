package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  16:12
 **/
public record TokenRequestDTO(
        @NotBlank @NonNull String username,
        @NotBlank @NonNull String password
)
        implements DTO {
}
