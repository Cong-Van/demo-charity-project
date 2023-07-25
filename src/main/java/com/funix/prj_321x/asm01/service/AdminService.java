package com.funix.prj_321x.asm01.service;

import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.Role;
import com.funix.prj_321x.asm01.entity.User;
import com.funix.prj_321x.asm01.entity.UserDonation;

import java.util.List;

public interface AdminService {

    List<Role> getAllRoles();

    List<User> getAllUsers();

    void saveUser(User user);

    User findUserById(int userId);

    void deleteUserByID(int userId);

    void updateUser(int userId, String fullName, String phoneNumber, String address, int roleId);

    List<Donation> getAllDonations();

    void saveDonation(Donation newDonation);

    void deleteDonationByID(int donationId);

    Donation getDonationById(int donationId);

    UserDonation getUserDonationById(int userDonationId);

    void saveUserDonation(UserDonation userDonation);

    void deleteUserDonation(UserDonation userDonation);
}
