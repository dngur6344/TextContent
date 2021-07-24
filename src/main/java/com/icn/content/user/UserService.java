package com.icn.content.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
