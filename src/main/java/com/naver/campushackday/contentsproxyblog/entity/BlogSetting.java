package com.naver.campushackday.contentsproxyblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlogSetting {
	@Id
	@GeneratedValue
	private Integer id;
	private String title;

	public BlogSetting() {
	}

	public BlogSetting(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
