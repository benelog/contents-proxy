package com.naver.campushackday.contentsproxyblog.persistence;

import com.naver.campushackday.contentsproxyblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
