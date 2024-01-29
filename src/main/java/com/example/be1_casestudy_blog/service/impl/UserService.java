package com.example.be1_casestudy_blog.service.impl;

import com.example.be1_casestudy_blog.model.User;
import com.example.be1_casestudy_blog.repository.IUserRepository;
import com.example.be1_casestudy_blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iuserRepository;
    @Override
    public Iterable<User> findAll() {
        return iuserRepository.findAll();
    }
    @Override
    public void save(User user) {
        iuserRepository.save(user);
    }
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public User findUserByGmailAndPassword(String gmail, String password) {
        return iuserRepository.findByGmailAndPassword(gmail, password);
    }
}
