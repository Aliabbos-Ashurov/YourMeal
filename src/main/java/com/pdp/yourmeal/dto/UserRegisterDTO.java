package com.pdp.yourmeal.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:02
 **/
public record UserRegisterDTO(
        @NonNull String fullname,
        @NonNull String username,
        @NonNull String password,
        @NonNull String phone
) implements DTO {
}