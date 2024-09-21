package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:02
 **/
public record UserRegisterDTO(
        @NotBlank @NonNull String fullname,
        @NotBlank @NonNull String username,
        @NotBlank @NonNull String password,
        @NotBlank @NonNull String phone
) implements DTO {
}
