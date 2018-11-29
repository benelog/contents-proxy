package com.naver.campushackday.contentsproxyblog.util;

public class StringParser {

	public static String[] parseGithubUrl(String url){
		return url.split("/blob/master");
	}
}
