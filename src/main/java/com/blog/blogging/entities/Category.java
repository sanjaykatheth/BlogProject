package com.blog.blogging.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {


	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY	)
	private Integer categoryId;

	private String categoryTitle;

	private String  categoryDesc;

	
	 @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY	)
	 private List<Post> posts=new ArrayList<>();

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
	public Category(Integer categoryId, String categoryTitle, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


}
