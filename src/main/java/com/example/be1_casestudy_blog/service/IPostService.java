package com.example.be1_casestudy_blog.service;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;

public interface IPostService extends IGenerateService<Post>{
    Iterable<Post> findByUse(User user);
    Iterable<Post> findAllByTitleContaining(String title);
    Iterable<Post> findAllByCategory(String category);
}
