package com.vaya20.backend.PraiseRecording.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="praise_recording_file")
public class PraiseRecordingFile {
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="file")
	private byte[] file;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="praiseRecordingFile")
	private PraiseRecording praiseRecordingId;

	public PraiseRecordingFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PraiseRecordingFile(long id, byte[] file, PraiseRecording praiseRecordingId) {
		super();
		this.id = id;
		this.file = file;
		this.praiseRecordingId = praiseRecordingId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public PraiseRecording getPraiseRecordingId() {
		return praiseRecordingId;
	}

	public void setPraiseRecordingId(PraiseRecording praiseRecordingId) {
		this.praiseRecordingId = praiseRecordingId;
	}
}
