package com.blog.blogging.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blogging.payloads.ApiReponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resouceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=	ex.getMessage();
		ApiReponse apiRes=new ApiReponse(message, false); 
		return  new ResponseEntity(apiRes,HttpStatus.NOT_FOUND);
			
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotArgException(MethodArgumentNotValidException ex){
	
		Map<String,String> res=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
		String message=	error.getDefaultMessage();	
		res.put(fieldName, message);
		});
 		return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiException> handleApiException(ResourceNotFoundException ex)
	{
		String message=	ex.getMessage();
		ApiReponse apiRes=new ApiReponse(message, true); 
		return  new ResponseEntity(apiRes,HttpStatus.BAD_REQUEST);
			
	}
}
