package com.deepmindz.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deepmindz.dto.ApiResponse;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleCustomException(ResourceNotFoundException ex){
		
		Map<String,String> data  = new HashMap<>();
		String message = ex.getMessage();
		data.put("User", "Not found");
		data.put("Status", "Failled..");
		data.put("Token", "NA");
		ApiResponse apiResponsere = new ApiResponse(404,message,data);
		return new ResponseEntity<ApiResponse>(apiResponsere,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IllegalStateEx.class)
	public ResponseEntity<ApiResponse> handleCustomException(IllegalStateEx ex){
		ApiResponse apiResponsere = new ApiResponse();
		Map<String,String> data  = new HashMap<>();
		data.put("User", "Unknown");
		data.put("Status", "Failled..");
		data.put("Token", "NA");
		apiResponsere.setStatus(404);
		apiResponsere.setMessage("Invalid User");
		apiResponsere.setData(data);
		return new ResponseEntity<>(apiResponsere,HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleCustomException(Exception ex){
		ApiResponse apiResponsere = new ApiResponse();
		Map<String,String> data  = new HashMap<>();
		data.put("User", "Unknown");
		data.put("Status", "Failled..");
		data.put("Token", "NA");
		apiResponsere.setStatus(500);
		apiResponsere.setMessage("Invalid User");
		apiResponsere.setData(data);
		return new ResponseEntity<>(apiResponsere,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
