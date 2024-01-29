package com.example.be1_casestudy_blog.service;

import com.example.be1_casestudy_blog.model.User;

public interface IUserService extends IGenerateService<User> {
    User findUserByGmailAndPassword(String gmail, String password);

}
