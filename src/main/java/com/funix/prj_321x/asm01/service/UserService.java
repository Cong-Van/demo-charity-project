package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.UserDonation;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<Donation> findPagination(int pageNo, int pageSize);

    Donation getDonationById(int donationId);

    void saveUserDonation(UserDonation newUserDonation);
}
