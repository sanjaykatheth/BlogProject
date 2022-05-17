
package com.blog.blogging.service;

import java.util.List;

import com.blog.blogging.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
	void deleteCategory(Integer catId);
	CategoryDto getCateogry(Integer catId);
	List<CategoryDto> getCategories();
}
