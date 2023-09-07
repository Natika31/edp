package com.projet.edp.fileViewer.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;
import org.hibernate.annotations.JdbcTypeCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_content")
public class FileContent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long file_content_id;
	
	@Lob
	@JdbcTypeCode(Types.VARBINARY)
	@Column(name = "binary_content")
	private byte[] binary_content;

	public FileContent() {
		super();
	}
	
	public FileContent(byte[] binary_content) {
		super();
		this.binary_content = binary_content;
	}

	public Long getFile_content_id() {
		return file_content_id;
	}

	public void setFile_content_id(Long file_content_id) {
		this.file_content_id = file_content_id;
	}

	public byte[] getBinary_content() {
		return binary_content;
	}

	public void setBinary_content(byte[] binary_content) {
		this.binary_content = binary_content;
	}
	
	public byte[] convertInputFileToBinaryArray(String file_origin_path) throws FileNotFoundException, IOException {
		File PDFfile = new File(file_origin_path);
		FileInputStream fileInputStream = new FileInputStream(PDFfile);
		byte[] binaryArray = new byte[(int)PDFfile.length()];
		fileInputStream.read(binaryArray);
		fileInputStream.close();
		return binaryArray;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(binary_content);
		result = prime * result + Objects.hash(file_content_id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileContent other = (FileContent) obj;
		return Arrays.equals(binary_content, other.binary_content)
				&& Objects.equals(file_content_id, other.file_content_id);
	}


	@Override
	public String toString() {
		return "FileContent [file_content_id=" + file_content_id + ", binary_content=" + Arrays.toString(binary_content)
				+ "]";
	}
		
}
