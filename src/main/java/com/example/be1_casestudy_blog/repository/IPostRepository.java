package com.example.be1_casestudy_blog.repository;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends CrudRepository<Post, Long>{
    List<Post> findAllByUser(User user);
    List<Post> findAllByTitleContaining(String title);
    List<Post> findAllByCategory(String category);
}
