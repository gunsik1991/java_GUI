package com.my.guijtable;

public class BookDTO {

	int booknum;
	String bookname;
	String title;  //출판사
	String author;  //저자
	int price;
	int stock_num;//재고량
	int total_price; //총 합계
	public BookDTO() {
		
	}
	public BookDTO(int booknum, String bookname, String title, String author, int price, int stock_num,
			int total_price) {
		super();
		this.booknum = booknum;
		this.bookname = bookname;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock_num = stock_num;
		this.total_price = total_price;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock_num() {
		return stock_num;
	}
	public void setStock_num(int stock_num) {
		this.stock_num = stock_num;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	
	
	
}
