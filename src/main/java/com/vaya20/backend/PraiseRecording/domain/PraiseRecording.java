package com.vaya20.backend.PraiseRecording.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="praise_recording")
public class PraiseRecording {
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@JsonIgnore
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="praise_recording_file_id")
	private PraiseRecordingFile praiseRecordingFile;
	
	@Column(name="body")
	private String body;
	
	@Column(name="delete_yn")
	private char deleteYN;

	public PraiseRecording() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PraiseRecording(long id, String author, String title, String date, PraiseRecordingFile praiseRecordingFile,
			String body, char deleteYN) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.date = date;
		this.praiseRecordingFile = praiseRecordingFile;
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

	public PraiseRecordingFile getPraiseRecordingFile() {
		return praiseRecordingFile;
	}

	public void setPraiseRecordingFile(PraiseRecordingFile praiseRecordingFile) {
		this.praiseRecordingFile = praiseRecordingFile;
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
