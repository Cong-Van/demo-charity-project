package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.dao.UserRepository;
import com.funix.prj_321x.asm01.entity.User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{

    private UserRepository userRepository;

    public LoginServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmailPassword(String email, String password) {
        return userRepository.findByEmailPassword(email, password);
    }
}
