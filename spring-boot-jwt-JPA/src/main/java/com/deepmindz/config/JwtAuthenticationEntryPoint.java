package com.deepmindz.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.deepmindz.dto.ApiResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		ApiResponse apiResponsere = new ApiResponse();
		Map<String, String> data = new HashMap<>();
		data.put("User", "Admin");
		data.put("Status", "Authorized User");
		data.put("Token", "NA");
		apiResponsere.setStatus(200);
		apiResponsere.setMessage("Success..");
		apiResponsere.setData(data);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "" + apiResponsere);
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}