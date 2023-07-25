package com.funix.prj_321x.asm01.controller;

import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.Role;
import com.funix.prj_321x.asm01.entity.User;
import com.funix.prj_321x.asm01.entity.UserDonation;
import com.funix.prj_321x.asm01.service.AdminService;
import com.funix.prj_321x.asm01.service.AdminServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/home")
    public String showHome() {

        return "admin/home";
    }

    // User management
    @GetMapping("/showUsers")
    public String showUsers(Model theModel) {

        List<Role> roles = adminService.getAllRoles();
        List<User> users = adminService.getAllUsers();

        theModel.addAttribute("roles", roles);
        theModel.addAttribute("users", users);

        // Đưa vào sẵn 1 user mới để ghi thông tin với modal
        theModel.addAttribute("newUser", new User());

        return "admin/account";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") User user, RedirectAttributes theRedirectModel) {

        adminService.saveUser(user);

        // Thông báo khi thêm mới user
        theRedirectModel.addFlashAttribute("success", "Thêm mới thành công!");

        return "redirect:/admin/showUsers";
    }

    @PostMapping("/send-mail")
    public String sendEmail(@RequestParam("userId") int userId, @RequestParam("note") String note) {

        User user = adminService.findUserById(userId);
        user.setNote(note);

        adminService.saveUser(user);

        return "redirect:/admin/showUsers";
    }

    @PostMapping("/lock-unlock")
    public String lockUser(@RequestParam("userId") int userId) {

        User user = adminService.findUserById(userId);
        user.setStatus(user.getStatus() == 0 ? 1 : 0);

        adminService.saveUser(user);

        return "redirect:/admin/showUsers";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int userId) {

        adminService.deleteUserByID(userId);

        return "redirect:/admin/showUsers";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") int userId,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address,
                             @RequestParam("roleId") int roleId) {

        adminService.updateUser(userId, fullName, phoneNumber, address, roleId);

        return "redirect:/admin/showUsers";
    }

    // Donation management
    @GetMapping("/showDonations")
    public String showDonations(Model theModel) {

        List<Donation> donations = adminService.getAllDonations();

        theModel.addAttribute("donations", donations);

        // Thêm sẵn 1 đợt quyên góp để ghi thông tin với modal
        theModel.addAttribute("newDonation", new Donation());

        return "admin/donation";
    }

    @PostMapping("/addDonation")
    public String addDonation(@ModelAttribute("newDonation") Donation newDonation, RedirectAttributes theModel) {

        adminService.saveDonation(newDonation);

        theModel.addFlashAttribute("success", "Thêm mới thành công!");

        return "redirect:/admin/showDonations";
    }

    @PostMapping("/deleteDonation")
    public String deleteDonation(@RequestParam("donationId") int donationId) {

        adminService.deleteDonationByID(donationId);

        return "redirect:/admin/showDonations";
    }

    @PostMapping("/updateDonation")
    public String updateDonation(@ModelAttribute("newDonation") Donation newDonation) {

        // File HTML có input hidden lấy id để cập nhật
        adminService.saveDonation(newDonation);

        return "redirect:/admin/showDonations";
    }

    @PostMapping("/changeDonationStatus")
    public String changeDonationStatus(@RequestParam("donationId") int donationId) {

        Donation donation = adminService.getDonationById(donationId);

        // Chuyển tiếp trạng thái cho đợt quyên góp
        // Khi đã ở trạng thái cuối mà đóng thì trở về 0
        int status = donation.getStatus();
        if (status == 3) {
            status = -1;
        }

        donation.setStatus(status + 1);
        adminService.saveDonation(donation);

        return "redirect:/admin/showDonations";
    }

    @GetMapping("/detailDonation")
    public String showDetailDonation(@RequestParam("donationId") int donationId, Model theModel) {

        Donation donation = adminService.getDonationById(donationId);

        theModel.addAttribute("donation", donation);
        theModel.addAttribute("userDonations", donation.getUserDonations());

        return "admin/detail";
    }

    @PostMapping("/confirmAddUserDonation")
    public String confirmAddUserDonation(@RequestParam("userDonationId") int userDonationId, Model theModel) {

        // Xác thực lượt quyên góp và chuyển trạng thái
        UserDonation userDonation = adminService.getUserDonationById(userDonationId);
        userDonation.setStatus(1);
        adminService.saveUserDonation(userDonation);

        // Sau xác thực thì thêm tiền vào đợt quyên góp
        Donation donation = userDonation.getDonation();
        int money = donation.getMoney();
        donation.setMoney(money + userDonation.getMoney());
        adminService.saveDonation(donation);

        return showDetailDonation(donation.getId(), theModel);
    }

    @PostMapping("/cancelAddUserDonation")
    public String cancelAddUserDonation(@RequestParam("userDonationId") int userDonationId, Model theModel) {

        UserDonation userDonation = adminService.getUserDonationById(userDonationId);
        Donation donation = userDonation.getDonation();

        // Xóa lượt quyên góp
        adminService.deleteUserDonation(userDonation);


        return showDetailDonation(donation.getId(), theModel);
    }
}
