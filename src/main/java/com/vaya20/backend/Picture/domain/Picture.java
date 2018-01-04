package com.vaya20.backend.Picture.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="picture")
public class Picture {
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@Column(name="file")
	private byte[] file;
	
	@Column(name="body")
	private String body;
	
	@Column(name="delete_yn")
	private char deleteYN;
	

	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Picture(long id, String author, String title, String date, byte[] file, String body, char deleteYN) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.date = date;
		this.file = file;
		this.body = body;
		this.deleteYN = deleteYN;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}
}
