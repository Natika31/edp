package com.projet.edp.fileTree.dto;

public class FileDTO{
	
	private String item_id;
	
	private String item_name;
	
	private String item_local_path;
	
	private String file_format;
	
	private String file_origin_path;
		
	private byte[] binary_content;
	
	public FileDTO() {
		super();
	}

	public FileDTO(String item_id, String item_name, String item_local_path, String file_format, String file_origin_path,
			byte[] binary_content) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
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

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
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

}
