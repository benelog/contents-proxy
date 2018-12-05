package com.naver.campushackday.contentsproxyblog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTimeConverter {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String toStringDateTime(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
