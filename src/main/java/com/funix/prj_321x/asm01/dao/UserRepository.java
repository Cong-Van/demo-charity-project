package com.funix.prj_321x.asm01.dao;

import com.funix.prj_321x.asm01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value="SELECT * FROM users u WHERE u.email=?1 AND u.password=?2", nativeQuery = true)
    User findByEmailPassword(String email, String password);
}
