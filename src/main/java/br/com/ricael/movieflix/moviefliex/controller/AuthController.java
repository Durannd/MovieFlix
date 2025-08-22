package br.com.ricael.movieflix.moviefliex.controller;

import br.com.ricael.movieflix.moviefliex.controller.request.UserRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.UserResponse;
import br.com.ricael.movieflix.moviefliex.mapper.UserMapper;
import br.com.ricael.movieflix.moviefliex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse>  register(@RequestBody UserRequest request){
        userService.save(UserMapper.toUser(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(userService.save(UserMapper.toUser(request))));
    }
}
