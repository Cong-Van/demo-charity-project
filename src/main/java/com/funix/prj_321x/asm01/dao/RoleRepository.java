package com.funix.prj_321x.asm01.dao;

import com.funix.prj_321x.asm01.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
