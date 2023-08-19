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
@DiscriminatorValue(value = "FILE")
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "file")
public class MyFile extends FileTreeItem implements Serializable {

	@Column(name = "file_format")
	private String file_format;

	@Column(name = "file_origin_path")
	private String file_origin_path;

	@OneToOne
	@JoinColumn(name = "file_content_fk", nullable=false)
	private FileContent file_content;

	private String item_type;

	public MyFile() {
		this.setItem_type(this.getClass().toString());
	}

	public MyFile(String item_name, String item_local_path, String file_format, String file_origin_path, FileContent fileContent)throws FileNotFoundException, IOException {
		super(item_name, item_local_path);
		this.file_format = file_format;
		this.file_origin_path = file_origin_path;
		this.file_content = fileContent;
		this.setItem_type(this.getClass().toString());
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

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	@Override
	public String toString() {
		return "MyFile [item_id=" + super.getItem_id() + ", file_format="
				+ file_format + ", file_origin_path=" + file_origin_path + ", item_type=" + item_type + ", file_content=" + file_content + "]";
	}

}
