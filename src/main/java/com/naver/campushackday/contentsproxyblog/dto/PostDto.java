package com.naver.campushackday.contentsproxyblog.dto;

import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.util.DateTimeConverter;
import lombok.Getter;

@Getter
public class PostDto {

	private Long id;
	private String title;
	private String content;
	private Long viewCount;
	private String createdDate;

	public PostDto(Post entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.viewCount = entity.getViewCount();
		this.createdDate = DateTimeConverter.toStringDateTime(entity.getCreatedDate());
	}

}
