package com.funix.prj_321x.asm01.controller;

import com.funix.prj_321x.asm01.entity.User;
import com.funix.prj_321x.asm01.service.AdminServiceImp;
import com.funix.prj_321x.asm01.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private LoginService loginService;

    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/")
    public String showLogin() {

        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model theModel) {

        User user = loginService.findUserByEmailPassword(email, password);

        // Xác thực đăng nhập với email và pass, đưa vào các trang tương ứng với chức năng

        if (user == null || user.getStatus() == 0) {
            theModel.addAttribute("error", true);
            return "forward:/";
        } else if (user.getRole().getId() == 1) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/user/home";
        }

    }
}
