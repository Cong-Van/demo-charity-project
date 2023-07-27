package com.funix.prj_321x.asm01.dao;

import com.funix.prj_321x.asm01.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
