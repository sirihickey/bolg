package com.bdqn.interceptors;

import com.bdqn.utils.JwtHelper;
import com.bdqn.utils.Result;
import com.bdqn.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录保护拦截器，检查请求头中是否包含有校token
 *
 * 有效，放行
 * 没有，无效，返回504
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtHelper jwtHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//从请求头中获取token
		String token = request.getHeader("token");
		//检查是否有效
		boolean expiration = jwtHelper.isExpiration(token);
		//有效放行
		if (!expiration) {
			//放行，没有过期
			return true;
		}

		//无效返回504的状态json
		Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
		ObjectMapper objectMapper =new JsonMapper();
		String json = objectMapper.writeValueAsString(result);
		response.getWriter().print(json);
		return false;
	}
}
