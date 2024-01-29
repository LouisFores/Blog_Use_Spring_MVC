package com.example.be1_casestudy_blog.controller;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.PostForm;
import com.example.be1_casestudy_blog.model.User;
import com.example.be1_casestudy_blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class PostController {
    @Autowired
    private IPostService postService;

    @Value("${file-upload}")
    private String fileUpload;

    public static final List<String> categoryList = new ArrayList<>();
    static {
        categoryList.add(".mp3");
        categoryList.add(".mp4");
        categoryList.add(".jsp");
    }

    @GetMapping("")
    public ModelAndView viewPost() {
        ModelAndView modelAndView = new ModelAndView("/home/home");
        List<Post> postList = (List<Post>) postService.findAll();
        List<Post> trendingPosts = postList.subList(0, 5);
        modelAndView.addObject("postList", postList);
        modelAndView.addObject("trendingPosts", trendingPosts);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchPosts(@RequestParam("search") Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("/home/home");
        List<Post> trendingPosts = ((List<Post>) postService.findAll()).subList(0, 5);
        if (search.isPresent()) {
            List<Post> postList = (List<Post>) postService.findAllByTitleContaining(search.get());
            modelAndView.addObject("trendingPosts", trendingPosts);
            modelAndView.addObject("postList", postList);
            return modelAndView;
        }
        return new ModelAndView("redirect:/blog");
    }


    @GetMapping("/search/{topic}")
    public ModelAndView searchTopic(@PathVariable String topic) {
        if (topic != null) {
            ModelAndView modelAndView = new ModelAndView("/home/home");
            List<Post> trendingPosts = ((List<Post>) postService.findAll()).subList(0, 5);
            List<Post> postList = (List<Post>) postService.findAllByCategory(topic);
            modelAndView.addObject("trendingPosts", trendingPosts);
            modelAndView.addObject("postList", postList);
            return modelAndView;
        }
        return new ModelAndView("redirect:/blog");
    }

    @GetMapping("/read-more/{id}")
    public ModelAndView readMorePost(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/single/single");
            modelAndView.addObject("post", post.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/blog");
        }
    }

    @GetMapping("/create-post")
    public ModelAndView createPostForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("postForm", new PostForm());
        return modelAndView;
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute PostForm postForm, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            MultipartFile multipartImgTitle = postForm.getMultipartImgTitle();
            MultipartFile multipartAudio = postForm.getMultipartAudio();
            String fileImgTitle = multipartImgTitle.getOriginalFilename();
            String fileAudio = multipartAudio.getOriginalFilename();
            try {
                FileCopyUtils.copy(postForm.getMultipartImgTitle().getBytes(), new File((fileUpload + fileImgTitle)));
                FileCopyUtils.copy(postForm.getMultipartAudio().getBytes(), new File((fileUpload + fileAudio)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Post post = new Post(postForm.getId_post(),
                    postForm.getTitle(),
                    postForm.getTimeCreate(),
                    postForm.getSummary(),
                    postForm.getMainContent(),
                    postForm.getCategory(),
                    fileImgTitle,
                    fileAudio,
                    user);
            postService.save(post);
        }
        return "redirect:/blog";
     }
    @GetMapping("/update-post/{id}")
    public ModelAndView updatePost(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/update");
        Post post = postService.findById(id).get();
        PostForm postForm = new PostForm(post.getId_post(), post.getTitle(), post.getSummary(),
                post.getMainContent(), post.getCategory(),
                new PostForm().getMultipartImgTitle(),
                new PostForm().getMultipartAudio());

        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("postForm", postForm);
        return modelAndView;
    }

    @GetMapping("/delete-post/{id}")
    public String deleteContent(@PathVariable Long id) {
        postService.remove(id);
        return "redirect:/blog";
    }


}
