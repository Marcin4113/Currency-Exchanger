package com.marcin.currencyexchanger.user;

import com.marcin.currencyexchanger.authentication.dto.RegisterRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "encodedPassword", target = "password")
    User toUser(RegisterRequestDTO registerRequestDTO, String encodedPassword);
}
