package me.hossainbillal.bookstore.uicontroller;

import me.hossainbillal.bookstore.domain.model.User;
import me.hossainbillal.bookstore.domain.security.ResetPasswordToken;
import me.hossainbillal.bookstore.service.impl.UserSecurityService;
import me.hossainbillal.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
/*
    @RequestMapping("/account")
    public String account() {
        return "account/account";
    }
*/

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "account/account";
    }

    @RequestMapping("/resetPassword")
    public String resetPassowrd(
            Model model) {

        model.addAttribute("classActiveResetPassword", true);
        return "account/account";
    }

    @RequestMapping("/register")
    public String register(
            Locale locale,
            @RequestParam("token") String token,
            Model model) {

        ResetPasswordToken passwordToken = userService.getPasswordToken(token);

        // If there is no valid token, redirect to the bad request page
        if (passwordToken == null) {
            String message = "INVALID TOKEN!";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }
        // If it is a valid token, set current session to the new user
        User user = passwordToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        model.addAttribute("classActiveEdit", true);
        return "account/profile";
    }

    @RequestMapping("/recoverPassword")
    public String recoverPass(Model model) {
        model.addAttribute("classActiveRecoverPassword", true);
        return "account/profile";
    }
}
