package com.example.demo.whiskies;

import java.util.List;

public class WhiskiesResponse{
	private List<Whisky> whiskiesResponse;

	public WhiskiesResponse(){

	}
	private int total;
	private int page;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setWhiskiesResponse(List<Whisky> whiskiesResponse){
		this.whiskiesResponse = whiskiesResponse;
	}

	public List<Whisky> getWhiskiesResponse(){
		return whiskiesResponse;
	}
}