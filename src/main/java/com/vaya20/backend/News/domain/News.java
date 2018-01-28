package com.vaya20.backend.News.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class News {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="title1")
	private String title1;
	
	@Column(name="title2")
	private String title2;
	
	@Column(name="title3")
	private String title3;
	
	@Column(name="title4")
	private String title4;
	
	@Column(name="title5")
	private String title5;
	
	@Column(name="description1")
	private String description1;
	
	@Column(name="description2")
	private String description2;
	
	@Column(name="description3")
	private String description3;
	
	@Column(name="description4")
	private String description4;
	
	@Column(name="description5")
	private String description5;
	
	@Column(name="delete_yn")
	private char deleteYN;

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Long id, String title1, String title2, String title3, String title4, String title5, String description1,
			String description2, String description3, String description4, String description5,char deleteYN) {
		super();
		this.id = id;
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;
		this.title4 = title4;
		this.title5 = title5;
		this.description1 = description1;
		this.description2 = description2;
		this.description3 = description3;
		this.description4 = description4;
		this.description5 = description5;
		this.deleteYN = deleteYN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public String getTitle5() {
		return title5;
	}

	public void setTitle5(String title5) {
		this.title5 = title5;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	public String getDescription4() {
		return description4;
	}

	public void setDescription4(String description4) {
		this.description4 = description4;
	}

	public String getDescription5() {
		return description5;
	}

	public void setDescription5(String description5) {
		this.description5 = description5;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}
}
