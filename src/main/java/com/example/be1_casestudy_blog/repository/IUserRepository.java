package com.example.be1_casestudy_blog.repository;

import com.example.be1_casestudy_blog.model.Post;
import com.example.be1_casestudy_blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, Long> {
//    @Query(value = "SELECT * FROM user u WHERE u.gmail = :emailUser AND u.password = :passwordUser", nativeQuery = true)
//    User findByGmailAndPassword(@Param("emailUser") String emailUser, @Param("passwordUser") String passwordUser);
//
    User findByGmailAndPassword(String gmail, String password);
}
