package com.pdp.yourmeal.mapper;

import com.pdp.yourmeal.dto.UserDTO;
import com.pdp.yourmeal.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  08:46
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO dto);

    UserDTO toUserDTO(User user);
}
