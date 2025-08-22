package br.com.ricael.movieflix.moviefliex.mapper;

import br.com.ricael.movieflix.moviefliex.controller.request.UserRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.UserResponse;
import br.com.ricael.movieflix.moviefliex.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse (User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
