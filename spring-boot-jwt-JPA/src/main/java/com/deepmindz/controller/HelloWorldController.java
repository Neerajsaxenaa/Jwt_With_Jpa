package com.deepmindz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepmindz.dto.ApiResponse;

@RestController
public class HelloWorldController {

	
	@RequestMapping({ "/test" })
	public ApiResponse test() {
		ApiResponse   dto = new ApiResponse();
		Map<String,String> map = new HashMap<>();
		map.put("User", "Admin");
		map.put("Status", "Authorize");
		map.put("Output", "Hello this is from  TestApi response.. ");
		dto.setStatus(200);
		dto.setMessage("Suceess");
		dto.setData(map);
		return dto;
		
		
	}

}