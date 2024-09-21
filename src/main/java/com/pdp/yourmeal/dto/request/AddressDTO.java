package com.pdp.yourmeal.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  20:49
 **/
public record AddressDTO(
        @NotBlank @NonNull String street,
        @NotBlank @NonNull String apartmentNumber,
        @NotBlank @NonNull String buildingNumber,
        @NotBlank @NonNull String intercom
) {
}