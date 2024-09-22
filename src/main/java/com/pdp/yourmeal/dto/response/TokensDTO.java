package com.pdp.yourmeal.dto.response;

import com.pdp.yourmeal.dto.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 22/September/2024  10:38
 **/
public record TokensDTO(@NotBlank @NonNull String accessToken) implements DTO {
}
