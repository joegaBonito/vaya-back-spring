package com.vaya20.backend.YAColumn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yacolumn")
public class YAColumn {
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@Column(name="main_verse")
	private String mainVerse;
	
	@Column(name="body")
	private String body;
	
	@Column(name="delete_yn")
	private char deleteYN;

	public YAColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public YAColumn(long id, String author, String title, String date, String mainVerse, String body, char deleteYN) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.date = date;
		this.mainVerse = mainVerse;
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

	public String getMainVerse() {
		return mainVerse;
	}

	public void setMainVerse(String mainVerse) {
		this.mainVerse = mainVerse;
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
