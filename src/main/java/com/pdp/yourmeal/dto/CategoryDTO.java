package com.pdp.yourmeal.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  09:11
 **/
public record CategoryDTO(@NonNull String icon,
                          @NonNull String title) implements DTO {
}
