package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import com.pdp.yourmeal.entity.Address;
import com.pdp.yourmeal.enums.OrderType;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  11:16
 **/
public record ConfirmOrderDTO(
        @NonNull Long orderId,
        @NonNull String fullname,
        @NonNull String phone,
        @NonNull OrderType type,
        Address address
) implements DTO {
}
