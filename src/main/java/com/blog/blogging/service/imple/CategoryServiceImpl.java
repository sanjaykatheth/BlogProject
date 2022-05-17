package com.blog.blogging.service.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogging.entities.Category;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.CategoryDto;
import com.blog.blogging.repositories.CategoryRepo;
import com.blog.blogging.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {


	@Autowired
	private CategoryRepo catrepo;

	@Autowired
	private ModelMapper modelMap;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category=this.modelMap.map(categoryDto, Category.class);
		Category addedCat=this.catrepo.save(category);
		return this.modelMap.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
          
		
		Category cat=this.catrepo.findById(catId).orElseThrow(()->new ResourceNotFoundException
				                                     ("Category", "Category Id",catId));

		 cat.setCategoryDesc(categoryDto.getCategoryDesc());
		 cat.setCategoryTitle(categoryDto.getCategoryTitle());
		 this.catrepo.save(cat);
		 
		return this.modelMap.map(cat, categoryDto.getClass());
	}

	



	@Override
	public List<CategoryDto> getCategories() {
		List<Category> category=this.catrepo.findAll();
	
		List<CategoryDto> catDto=category.stream().map((cat)-> this.modelMap.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto	;
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category cat=this.catrepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","categiory ID", catId));
		
		this.catrepo.delete(cat);
	}

	@Override
	public CategoryDto getCateogry(Integer catId) {
		Category cat=this.catrepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","categiory ID", catId));;
		return this.modelMap.map(cat, CategoryDto.class);
	}

}
