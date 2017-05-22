package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Book {
	
	private int index;
	private String title;
	private String author;
	private int price;
	private int page;
	private int borrowed; //¾Èºô·Á°¬À¸¸é 0, ºô·Á°¬À¸¸é 1
	private BorrowInfo borrowInfo;
	

	public BorrowInfo getBorrowInfo() {
		return borrowInfo;
	}

	public void setBorrowInfo(BorrowInfo borrowInfo) {
		this.borrowInfo = borrowInfo;
	}

	public int getIndex(){
		return index;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAuthor(){
		return author;
	}
	
		

	
	public int getPrice(){
		return price;
	}
	
	public int getPage(){
		return page;
	}
	
	public int getBorrow(){
		return borrowed;
	}
	

	public void setAuthor(String author){
		this.author=author;
	}
	
	public void setIndex(int i){
		this.index=i;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public void setPage(int i){
		this.page=i;
	}
	public void setPrice(int i){
		this.price=i;
	}
	
	
	public void setBorrow(int b){
		this.borrowed=b;
	}
	

	

}
