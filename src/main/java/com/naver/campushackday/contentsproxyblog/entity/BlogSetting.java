package com.naver.campushackday.contentsproxyblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogSetting {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    public BlogSetting(String title){
        this.title = title;
    }
}
