package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.dao.DonationRepository;
import com.funix.prj_321x.asm01.dao.RoleRepository;
import com.funix.prj_321x.asm01.dao.UserDonationRepository;
import com.funix.prj_321x.asm01.dao.UserRepository;
import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.Role;
import com.funix.prj_321x.asm01.entity.User;
import com.funix.prj_321x.asm01.entity.UserDonation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private UserRepository userRepository;

    private DonationRepository donationRepository;

    private RoleRepository roleRepository;

    private UserDonationRepository userDonationRepository;

    public AdminServiceImp(UserRepository userRepository, DonationRepository donationRepository,
                           RoleRepository roleRepository, UserDonationRepository userDonationRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.userDonationRepository = userDonationRepository;
    }


    // User management
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserByID(int theId) {
        userRepository.deleteById(theId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserById(int theId) {
        return userRepository.findById(theId).get();
    }

    public void updateUser(int theId, String fullName, String phoneNumber, String address, int roleId) {
        User user = userRepository.findById(theId).get();
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setRole(roleRepository.findById(roleId).get());
        userRepository.save(user);
    }


    // Donation management
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public void saveDonation(Donation newDonation) {
        donationRepository.save(newDonation);
    }

    public void deleteDonationByID(int theId) {
        donationRepository.deleteById(theId);
    }

    public Donation getDonationById(int theId) {
        return donationRepository.findById(theId).get();
    }

    public User findUserByEmailPassword(String email, String password) {
        return userRepository.findByEmailPassword(email, password);
    }

    public UserDonation getUserDonationById(int theId) {
        return userDonationRepository.findById(theId).get();
    }

    public void saveUserDonation(UserDonation userDonation) {
        userDonationRepository.save(userDonation);
    }

    public void deleteUserDonation(UserDonation userDonation) {
        userDonationRepository.delete(userDonation);
    }
}
