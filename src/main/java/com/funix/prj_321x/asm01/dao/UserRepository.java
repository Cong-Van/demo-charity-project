package com.funix.prj_321x.asm01.dao;

import com.funix.prj_321x.asm01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value="SELECT * FROM user u WHERE u.email=?1 AND u.password=?2", nativeQuery = true)
    User findByEmailPassword(String email, String password);
}
