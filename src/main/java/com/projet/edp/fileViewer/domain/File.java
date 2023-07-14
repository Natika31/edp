/**
 * 
 */
package com.projet.edp.fileViewer.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
@Table(name = "file")
public class File implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long file_id;

	@Column(name = "file_path")
	private String file_path;
	
	@Column(name = "file_name")
	private String file_name;	

	public File() {
	}
	
	public File(String file_path, String file_name, String file_format) {
		this.file_path = file_path;
		this.file_name = file_name;
	}

	public Long getFile_id() {
		return file_id;
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

	@Override
	public int hashCode() {
		return Objects.hash(file_id, file_name, file_path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		return Objects.equals(file_id, other.file_id) && Objects.equals(file_name, other.file_name)
				&& Objects.equals(file_path, other.file_path);
	}

	@Override
	public String toString() {
		return "File [file_id=" + file_id + ", file_path=" + file_path + ", file_name=" + file_name + "]";
	}

	

}
