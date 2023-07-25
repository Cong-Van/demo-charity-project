package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.dao.DonationRepository;
import com.funix.prj_321x.asm01.dao.UserDonationRepository;
import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.UserDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private DonationRepository donationRepository;

    private UserDonationRepository userDonationRepository;

    public UserServiceImp(DonationRepository donationRepository, UserDonationRepository userDonationRepository) {
        this.donationRepository = donationRepository;
        this.userDonationRepository = userDonationRepository;
    }

    // Phân trang cho danh sách các đợt quyên góp, 5 đợt quyên góp 1 trang
    public Page<Donation> findPagination(int pageNo, int pageSize) {

        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);

        return donationRepository.findAll(pageable);
    }

    public List<Donation> getAllDonation() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(int theId) {
        return donationRepository.findById(theId).get();
    }

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public void saveUserDonation(UserDonation userDonation) {
        userDonationRepository.save(userDonation);
    }
}
