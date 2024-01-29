package com.example.be1_casestudy_blog.controller;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;
import com.example.be1_casestudy_blog.service.IPostService;
import com.example.be1_casestudy_blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("/login/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User userLogin, Model model, HttpSession session) {
        User user = userService.findUserByGmailAndPassword(userLogin.getGmail(), userLogin.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/blog";
        } else {
            model.addAttribute("message", "Not found the account");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        ModelAndView modelAndView = new ModelAndView("/login/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userService.findUserByGmailAndPassword(user.getGmail(), user.getPassword()) == null) {
            userService.save(user);
            model.addAttribute("message", "Create new customer successfully!");
        } else {
            model.addAttribute("message", "Account already exists!");
        }
        return "/login/register";
    }

}
