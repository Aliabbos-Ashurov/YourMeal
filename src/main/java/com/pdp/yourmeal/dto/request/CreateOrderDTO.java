package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  11:13
 **/
public record CreateOrderDTO(
        @NonNull Long userId,
        @NonNull Long productId,
        @NonNull Integer quantity
) implements DTO {
}
