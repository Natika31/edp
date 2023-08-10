/**
 * 
 */
package com.projet.edp.fileTree.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import jakarta.persistence.*;


/**
 * 
 */
@Entity
@DiscriminatorValue(value = "F")
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "file")
public class MyFile extends FileTreeItem implements Serializable {

	@Column(name = "file_local_path")
	private String file_local_path;
	
	@Column(name = "file_name")
	private String file_name;	

	@Column(name = "file_format")
	private String file_format;

	@Column(name = "file_origin_path")
	private String file_origin_path;

	@OneToOne
	@JoinColumn(name = "file_content_fk", nullable=false)
	private FileContent file_content;

	public MyFile() {
	}

	public MyFile(String file_local_path, String file_name, String file_format, String file_origin_path, FileContent fileContent)throws FileNotFoundException, IOException {
		super();
		this.file_local_path = file_local_path;
		this.file_name = file_name;
		this.file_format = file_format;
		this.file_origin_path = file_origin_path;
		this.file_content = fileContent;
	}

	//TODO: to generate from file name 
	public String getFile_file_local_path() {
		return file_local_path;
	}

	public void setFile_local_path(String file_local_path) {
		this.file_local_path = file_local_path;
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

	public String getFile_origin_path() {
		return file_origin_path;
	}

	public void setFile_origin_path(String file_origin_path) {
		this.file_origin_path = file_origin_path;
	}

	public FileContent getFile_content() {
		return file_content;
	}

	public void setFile_content(FileContent file_content) {
		this.file_content = file_content;
	}

	@Override
	public String toString() {
		return "MyFile [item_id=" + super.getItem_id() + ", file_local_path=" + file_local_path + ", file_name=" + file_name + ", file_format="
				+ file_format + ", file_origin_path=" + file_origin_path + ", file_content=" + file_content + "]";
	}

}
