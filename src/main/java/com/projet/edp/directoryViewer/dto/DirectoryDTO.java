package com.projet.edp.directoryViewer.dto;

import java.util.ArrayList;
import java.util.List;
import com.projet.edp.fileViewer.dto.FileDTO;

public class DirectoryDTO {

	private String directory_id;

	private String directory_local_path;

	private String directory_name;

	private List<FileDTO> childrenDTO;

	public DirectoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DirectoryDTO(String directory_id, String directory_local_path, String directory_name) {
		super();
		this.directory_id = directory_id;
		this.directory_local_path = directory_local_path;
		this.directory_name = directory_name;
		this.childrenDTO = new ArrayList<FileDTO>();
	}

	public String getDirectory_id() {
		return directory_id;
	}

	public void setDirectory_id(String directory_id) {
		this.directory_id = directory_id;
	}

	public String getDirectory_local_path() {
		return directory_local_path;
	}

	public void setDirectory_local_path(String directory_local_path) {
		this.directory_local_path = directory_local_path;
	}

	public String getDirectory_name() {
		return directory_name;
	}

	public void setDirectory_name(String directory_name) {
		this.directory_name = directory_name;
	}

	public List<FileDTO> getChildrenDTO() {
		return childrenDTO;
	}

	public void setChildren(List<FileDTO> childrenDTO) {
		this.childrenDTO = childrenDTO;
	}

	public void addChildrenDTO(FileDTO myFileDTO) {
		this.childrenDTO.add(myFileDTO);		
	}

	public void removeChildrenDTO(FileDTO myFileDTO) {
		this.childrenDTO.remove(myFileDTO);		
	}

}
