package com.projet.edp.fileTree.dto;

public class FileDTO {
	
	private String file_id;	
	
	private String file_name;	
		
	private byte[] binary_content;
	
	public FileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FileDTO(String file_id, String file_name, byte[] binary_content) {
		super();
		this.file_id = file_id;
		this.file_name = file_name;
		this.binary_content = binary_content;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public byte[] getBinary_content() {
		return binary_content;
	}

	public void setBinary_content(byte[] binary_content) {
		this.binary_content = binary_content;
	}

}
