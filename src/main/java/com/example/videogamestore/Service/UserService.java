package com.example.videogamestore.Service;


import com.example.videogamestore.Model.User;
import com.example.videogamestore.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public Iterable<User> getUsers(){
        return  userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(Long id, User user){
        User newUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        //newUser.setRole(user.getRole());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
    @Transactional
    public void deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }


    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
}
