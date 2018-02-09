package com.vaya20.backend.Picture.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	// @Column(name="file")
	// private byte[] file;
	
	@Column(name="original_file_name")
	private String originalFileName;
	
	@Column(name="body")
	private String body;
	
	@Column(name="delete_yn")
	private char deleteYN;
	
	@JsonIgnore
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="picture_list_id")
	private PictureList pictureList;
	
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Picture(long id, String author, String title, String date, String originalFileName, String body,
			char deleteYN, PictureList pictureList) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.date = date;
		//this.file = file;
		this.originalFileName = originalFileName;
		this.body = body;
		this.deleteYN = deleteYN;
		this.pictureList = pictureList;
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

	// public byte[] getFile() {
	// 	return file;
	// }

	// public void setFile(byte[] file) {
	// 	this.file = file;
	// }

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
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

	public PictureList getPictureList() {
		return pictureList;
	}

	public void setPictureList(PictureList pictureList) {
		this.pictureList = pictureList;
	}
}
