/**
 * 
 */
package com.projet.edp.fileViewer.domain;

import java.io.Serializable;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;
import org.hibernate.annotations.JdbcTypeCode;
import jakarta.persistence.*;


/**
 * 
 */
@Entity
@Table(name = "file")
public class MyFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long file_id;

	@Column(name = "file_path")
	private String file_path;

	@Column(name = "file_name")
	private String file_name;	

	@Column(name = "file_format")
	private String file_format;

	@Lob
	@JdbcTypeCode(Types.VARBINARY)
	@Column(name = "file_content")
	private byte[] file_content;

	public MyFile() {
	}

	public MyFile(String file_path, String file_name, String file_format, byte[] file_content) {
		super();
		this.file_path = file_path;
		this.file_name = file_name;
		this.file_format = file_format;
		this.file_content = file_content;
	}

	public Long getFile_id() {
		return file_id;
	}

	public void setFile_id(Long file_id) {
		this.file_id = file_id;		
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_format() {
		return file_format;
	}

	public void setFile_format(String file_format) {
		this.file_format = file_format;
	}

	public byte[] getFile_content() {
		return file_content;
	}

	public void setFile_content(byte[] file_content) {
		this.file_content = file_content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(file_content);
		result = prime * result + Objects.hash(file_format, file_id, file_name, file_path);
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
		MyFile other = (MyFile) obj;
		return Arrays.equals(file_content, other.file_content) && Objects.equals(file_format, other.file_format)
				&& Objects.equals(file_id, other.file_id) && Objects.equals(file_name, other.file_name)
				&& Objects.equals(file_path, other.file_path);
	}

	@Override
	public String toString() {
		return "File [file_id=" + file_id + ", file_path=" + file_path + ", file_name=" + file_name + ", file_format="
				+ file_format + ", file_content=" + Arrays.toString(file_content) + "]";
	}




}
