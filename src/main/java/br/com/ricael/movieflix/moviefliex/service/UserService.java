package br.com.ricael.movieflix.moviefliex.service;

import br.com.ricael.movieflix.moviefliex.entity.User;
import br.com.ricael.movieflix.moviefliex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }
}
