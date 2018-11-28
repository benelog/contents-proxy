package com.naver.campushackday.contentsproxyblog.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.naver.campushackday.contentsproxyblog.entity.BlogSetting;
import com.naver.campushackday.contentsproxyblog.persistence.BlogSettingRepository;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {
	@Mock
	BlogSettingRepository repository;
	@InjectMocks
	BlogService service;

	@Test
	public void shouldFindSetting_defaultTitle() {
		// when
		BlogSetting setting = service.findSetting();

		// then
		assertThat(setting.getTitle()).isEqualTo("제목 없는 블로그");
	}

	@Test
	public void shouldFindSetting() {
		// given
		var title = "핵데이";
		given(repository.findAll()).willReturn(List.of(new BlogSetting(title)));

		// when
		BlogSetting setting = service.findSetting();

		// then
		assertThat(setting.getTitle()).isEqualTo(title);
	}

}