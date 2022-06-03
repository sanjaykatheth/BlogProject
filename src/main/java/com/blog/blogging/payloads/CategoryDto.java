package com.blog.blogging.payloads;

public class CategoryDto {

	private Integer categoryId;
	private String categoryTitle;
	private String  categoryDesc;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
		


}
