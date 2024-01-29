package com.example.be1_casestudy_blog.service.impl;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;
import com.example.be1_casestudy_blog.repository.IPostRepository;
import com.example.be1_casestudy_blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository iPostRepository;
    @Override
    public Iterable<Post> findAll() {
        return iPostRepository.findAll();
    }
    @Override
    public void save(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iPostRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findByUse(User user) {
        return iPostRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return iPostRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findAllByCategory(String category) {
        return iPostRepository.findAllByCategory(category);
    }
}
