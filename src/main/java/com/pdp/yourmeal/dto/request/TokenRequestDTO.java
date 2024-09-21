package com.pdp.yourmeal.dto.request;

import com.pdp.yourmeal.dto.DTO;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  16:12
 **/
public record TokenRequestDTO(
        @NonNull String username,
        @NonNull String password
)
        implements DTO {
}
