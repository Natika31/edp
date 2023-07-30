package com.projet.edp.fileViewer.dto;

public class FileDTO {

	private String file_destination_path;

	private String file_name;	

	private String file_format;
		
	private byte[] binary_content;


	public String getFile_destination_path() {
		return file_destination_path;
	}

	public void setFile_destination_path(String file_destination_path) {
		this.file_destination_path = file_destination_path;
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

	public byte[] getBinary_content() {
		return binary_content;
	}

	public void setBinary_content(byte[] binary_content) {
		this.binary_content = binary_content;
	}

}
