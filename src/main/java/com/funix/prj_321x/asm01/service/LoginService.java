package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.entity.User;

public interface LoginService {
    User findUserByEmailPassword(String email, String password);
}
