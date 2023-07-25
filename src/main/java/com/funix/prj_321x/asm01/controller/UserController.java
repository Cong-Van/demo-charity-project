package com.funix.prj_321x.asm01.controller;

import com.funix.prj_321x.asm01.entity.Donation;
import com.funix.prj_321x.asm01.entity.UserDonation;
import com.funix.prj_321x.asm01.service.UserService;
import com.funix.prj_321x.asm01.service.UserServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String showHome(Model theModel) {

        return findPagination(1, theModel);
    }

    // Phân trang với 5 đợt quyên góp 1 trang
    @GetMapping("/page/{pageNo}")
    public String findPagination(@PathVariable("pageNo") int pageNo, Model theModel) {

        int pageSize = 5;

        Page<Donation> page = userService.findPagination(pageNo, pageSize);
        List<Donation> donations = page.getContent();

        theModel.addAttribute("donations", donations);

        // Đưa vào 1 lượt quyên góp mới để ghi thông tin với modal
        theModel.addAttribute("newUserDonation", new UserDonation());

        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", page.getTotalPages());

        return "public/home";
    }

    @PostMapping("/addUserDonation")
    public String addUserDonation(@RequestParam("donationId") int donationId,
                                  @ModelAttribute("newUserDonation") UserDonation newUserDonation) {

        // Thêm 1 lượt quyên góp vào đợt quyên góp xác thực bằng donationId
        Donation donation = userService.getDonationById(donationId);
        newUserDonation.setDonation(donation);
        userService.saveUserDonation(newUserDonation);

        return "redirect:/user/home";
    }

    @GetMapping("/donationDetail")
    public String showDonationDetail(@RequestParam("donationId") int donationId, Model theModel) {

        Donation donation = userService.getDonationById(donationId);

        theModel.addAttribute("donation", donation);
        theModel.addAttribute("userDonations", donation.getUserDonations());

        // Đưa vào sẵn 1 lượt quyên góp mới để ghi thông tin với modal
        theModel.addAttribute("newUserDonation", new UserDonation());

        return "public/detail";
    }

    @PostMapping("/addUserDonationInDetail")
    public String addUserDonationInDetail(@RequestParam("donationId") int donationId,
                                          @ModelAttribute("newUserDonation") UserDonation newUserDonation,
                                          Model theModel) {

        Donation donation = userService.getDonationById(donationId);
        newUserDonation.setDonation(donation);
        userService.saveUserDonation(newUserDonation);

        // Thêm thông báo quyên góp thành công
        theModel.addAttribute("msg", true);

        return showDonationDetail(donationId, theModel);
    }
}
