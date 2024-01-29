package com.example.be1_casestudy_blog.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PostForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    private String title;

    private LocalDate timeCreate = LocalDate.now();

    private String summary;

    @Column(length = 10000000)
    private String mainContent;

    private String category;

    private MultipartFile multipartImgTitle;
    private MultipartFile multipartAudio;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    public PostForm() {
    }

    public PostForm(Long id_post, String title, String summary, String mainContent, String category, MultipartFile multipartImgTitle, MultipartFile multipartAudio) {
        this.id_post = id_post;
        this.title = title;
        this.summary = summary;
        this.mainContent = mainContent;
        this.category = category;
        this.multipartImgTitle = multipartImgTitle;
        this.multipartAudio = multipartAudio;
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDate timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getMultipartImgTitle() {
        return multipartImgTitle;
    }

    public void setMultipartImgTitle(MultipartFile multipartImgTitle) {
        this.multipartImgTitle = multipartImgTitle;
    }

    public MultipartFile getMultipartAudio() {
        return multipartAudio;
    }

    public void setMultipartAudio(MultipartFile multipartAudio) {
        this.multipartAudio = multipartAudio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
