package com.blog.blogging.payloads;

import java.util.List;

public class PostsResponse {

	private List<PostDto> contents;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	public List<PostDto> getContents() {
		return contents;
	}
	public void setContents(List<PostDto> contents) {
		this.contents = contents;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long l) {
		this.totalElements = l;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public PostsResponse(List<PostDto> contents, int pageNo, int pageSize, int totalElements, int totalPages,
			boolean lastPage) {
		super();
		this.contents = contents;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	public PostsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
