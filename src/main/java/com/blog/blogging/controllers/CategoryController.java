package com.blog.blogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.payloads.ApiReponse;
import com.blog.blogging.payloads.CategoryDto;
import com.blog.blogging.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


	@Autowired
	private  CategoryService catService;

	@PostMapping("/")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDto catDto)
	{
		CategoryDto	newCat=this.catService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(newCat,HttpStatus.CREATED);
	}


	@PostMapping("/{catId}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto catDto,@PathVariable Integer catId)
	{
		CategoryDto updatedCat=this.catService.updateCategory(catDto, catId);
		return ResponseEntity.ok(updatedCat);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer catId)
	{
		this.catService.deleteCategory(catId);
		return new ResponseEntity<ApiReponse>(new ApiReponse("category is create",false),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<?> getCategory(@PathVariable Integer catId)
	{
		CategoryDto catDto=this.catService.getCateogry(catId);
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory()
	{
		List<CategoryDto> catDto=this.catService.getCategories();
		return ResponseEntity.ok(catDto);
	}
	
	
	@GetMapping
	public String sanjayData()
	{
		return "sanjaydata is as";
	}
	
}
