package com.naver.campushackday.contentsproxyblog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.naver.campushackday.contentsproxyblog.entity.BlogSetting;

public interface BlogSettingRepository extends JpaRepository<BlogSetting, Long> {
    String findTitleById(Long id);
}
