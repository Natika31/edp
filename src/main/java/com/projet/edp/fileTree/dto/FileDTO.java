package com.projet.edp.fileTree.dto;

import java.util.Arrays;

public class FileDTO{
	
	private String item_id;
	
	private String name;
	
	private String item_local_path;
	
	private String file_format;
	
	private String file_origin_path;
		
	private byte[] binary_content;
	
	public FileDTO() {
		super();
	}

	public FileDTO(String item_id, String name, String item_local_path, String file_format, String file_origin_path,
			byte[] binary_content) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.item_local_path = item_local_path;
		this.file_format = file_format;
		this.file_origin_path = file_origin_path;
		this.binary_content = binary_content;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_local_path() {
		return item_local_path;
	}

	public void setItem_local_path(String item_local_path) {
		this.item_local_path = item_local_path;
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

	public byte[] getBinary_content() {
		return binary_content;
	}

	public void setBinary_content(byte[] binary_content) {
		this.binary_content = binary_content;
	}

	@Override
	public String toString() {
		return "FileDTO [item_id=" + item_id + ", name=" + name + ", item_local_path=" + item_local_path
				+ ", file_format=" + file_format + ", file_origin_path=" + file_origin_path + ", binary_content="
				+ Arrays.toString(binary_content) + "]";
	}
	
}
