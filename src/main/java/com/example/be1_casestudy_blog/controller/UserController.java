package com.example.be1_casestudy_blog.controller;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;
import com.example.be1_casestudy_blog.service.IPostService;
import com.example.be1_casestudy_blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    public static final List<String> categoryList = new ArrayList<>();
    static {
        categoryList.add(".mp3");
        categoryList.add(".mp4");
        categoryList.add(".txt");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/blog/login";
    }

    @GetMapping("/page")
    public ModelAndView page(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/page/page");
        User user = (User) session.getAttribute("user");
        List<Post> listPostUser = (List<Post>) postService.findByUse(user);
        modelAndView.addObject("listPostUser", listPostUser);
        return modelAndView;
    }
}
