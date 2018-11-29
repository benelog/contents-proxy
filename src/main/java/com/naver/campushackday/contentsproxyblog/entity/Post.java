package com.naver.campushackday.contentsproxyblog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="TITLE")
    private String title;

    @Column(name ="GITHUB_URL")
    private String github_url;

    public Post(){}

    private Post(Long id, String title, String github_url){
        this.id = id;
        this.title = title;
        this.github_url= github_url;
    }

    public Post(String title, String github_url){
        this(0L, title, github_url);
    }


}
