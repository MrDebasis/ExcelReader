package com.reader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Details")
public class ArnatiSteelLimited {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer slNo;
	@Column(name = "DESCRIPTION")
	private String descriptionOfItem;
	@Column(name = "QNTY")
	private String qnty;
	@Column(name = "FILETYPE")
	private String fileType;

	@Transient
	private MultipartFile file;

	public ArnatiSteelLimited() {
		super();
	}

	public ArnatiSteelLimited(Integer slno, String descriptionOfItem, String qnty, String fileType) {
		super();

		this.descriptionOfItem = descriptionOfItem;
		this.qnty = qnty;
		this.fileType = fileType;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDescriptionOfItem() {
		return descriptionOfItem;
	}

	public void setDescriptionOfItem(String descriptionOfItem) {
		this.descriptionOfItem = descriptionOfItem;
	}

	public String getQnty() {
		return qnty;
	}

	public void setQnty(String qnty) {
		this.qnty = qnty;
	}

	@Override
	public String toString() {
		return "ArnatiSteelLimited [slno=" + ", descriptionOfItem=" + descriptionOfItem + ", qnty=" + qnty + "]";
	}

}
