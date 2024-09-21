package com.pdp.yourmeal.dto.response;

import com.pdp.yourmeal.dto.DTO;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  21:13
 **/
public record UserDTO(
        @NonNull Long id,
        @NonNull String fullname,
        @NonNull String username,
        @NonNull String phone
) implements DTO {
}
