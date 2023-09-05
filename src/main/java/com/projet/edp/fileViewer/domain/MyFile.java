/**
 * 
 */
package com.projet.edp.fileViewer.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import com.projet.edp.fileTree.domain.FileTreeItem;
import jakarta.persistence.*;

/**
 * 
 */
@Entity
@DiscriminatorValue(value = "FILE")
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "my_file")
public class MyFile extends FileTreeItem implements Serializable {
	
	public static final String FILE_TYPE = "file";
	
	private String item_type;

	@Column(name = "file_format")
	private String file_format;

	@Column(name = "file_origin_path")
	private String file_origin_path;

	@OneToOne
	@JoinColumn(name = "file_content_fk", nullable=false)
	private FileContent file_content;

	public MyFile() {
		this.item_type = FILE_TYPE;
	}

	public MyFile(String item_name, String item_local_path, String file_format, String file_origin_path, FileContent fileContent)throws FileNotFoundException, IOException {
		super(item_name, item_local_path);
		this.item_type = FILE_TYPE;
		this.file_format = file_format;
		this.file_origin_path = file_origin_path;
		this.file_content = fileContent;
	}
	
	public String getItem_type() {
		return item_type;
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
		return "MyFile ["+ super.toString() +"item_type=" + item_type + ", file_format=" + file_format + ", file_origin_path="
				+ file_origin_path + ", file_content=" + file_content + "]";
	}
	
}
