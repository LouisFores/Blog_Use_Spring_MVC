package com.example.be1_casestudy_blog.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    private String title;

    private LocalDate timeCreate = LocalDate.now();

    private String summary;

    @Column(length = 10000000)
    private String mainContent;

    private String category;

    private String fileImgTitle;
    private String fileAudio;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Post(){}

    public Post(Long id_post, String title, LocalDate timeCreate, String summary, String mainContent, String category, String fileImgTitle, String fileAudio, User user) {
        this.id_post = id_post;
        this.title = title;
        this.timeCreate = timeCreate;
        this.summary = summary;
        this.mainContent = mainContent;
        this.category = category;
        this.fileImgTitle = fileImgTitle;
        this.fileAudio = fileAudio;
        this.user = user;
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

    public String getFileImgTitle() {
        return fileImgTitle;
    }

    public void setFileImgTitle(String fileImgTitle) {
        this.fileImgTitle = fileImgTitle;
    }

    public String getFileAudio() {
        return fileAudio;
    }

    public void setFileAudio(String fileAudio) {
        this.fileAudio = fileAudio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
