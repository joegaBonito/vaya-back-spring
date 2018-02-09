package com.vaya20.backend.Picture.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="picture_list")
public class PictureList {
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="title")
	private String title;

	@Column(name="year")
	private String year;
	
	// @Column(name="file")
	// private byte[] file;
	
	@Column(name="original_file_name")
	private String originalFileName;
		
	@Column(name="delete_yn")
	private char deleteYN;
	
	@OneToMany( cascade={CascadeType.ALL}, mappedBy="pictureList")
	private List<Picture> picture;

	public PictureList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PictureList(long id, String title, String year, String originalFileName, char deleteYN,
			List<Picture> picture) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		//this.file = file;
		this.originalFileName = originalFileName;
		this.deleteYN = deleteYN;
		this.picture = picture;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	public List<Picture> getPicture() {
		return picture;
	}

	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}

}

