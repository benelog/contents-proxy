package com.naver.campushackday.contentsproxyblog.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (isAdmin(request)) {
			response.sendRedirect("/admin/loginForm");
			return false;
		}
		return true;
	}

	private boolean isAdmin(HttpServletRequest request) {
		return request.getSession().getAttribute("admin") == null;
	}
}
