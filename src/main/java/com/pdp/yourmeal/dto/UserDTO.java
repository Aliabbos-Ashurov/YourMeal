package com.pdp.yourmeal.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  21:13
 **/
public record UserDTO(
        @NonNull Long id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String username,
        @NonNull String phone
) implements DTO {
}
