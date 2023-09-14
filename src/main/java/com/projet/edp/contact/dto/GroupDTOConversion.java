package com.projet.edp.group.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.projet.edp.group.domain.MyGroup;
import com.projet.edp.user.domain.MyUser;
import com.projet.edp.user.dto.UserDTO;
import com.projet.edp.user.dto.UserDTOConversion;


public class GroupDTOConversion {
	public static final String GROUP_TYPE = "group";

	public static final String MEMBER_TYPE = "user";

	@Autowired
	private ModelMapper modelMapper;

	private UserDTOConversion userDTOConversion;

	public GroupDTOConversion() {
		super();
		this.modelMapper = new ModelMapper();
		this.userDTOConversion = new UserDTOConversion();
	}

	@Bean
	public GroupDTO convertEntityToDTO(MyGroup myGroup) {
		GroupDTO groupDTO = this.modelMapper.map(myGroup, GroupDTO.class);
		return groupDTO;
	}
	
	public List<GroupDTO> convertEntityToDTO(List<MyGroup> groups) {
		List<GroupDTO> groupDTOs = new ArrayList<>();
		for (MyGroup group : groups) {
			GroupDTO groupDTO = convertEntityToDTO(group);
			groupDTOs.add(groupDTO);
		}
		return groupDTOs;
	}

	@Bean
	public MyGroup convertDTOtoEntities(GroupDTO groupDTO) {
		MyGroup myGroup = modelMapper.map(groupDTO, MyGroup.class);
		return myGroup;
	}

	@Bean
	public List<MyUser> convertDTOtoEntitiesMembers(GroupDTO groupDTO){
		List<MyUser> newMembers = new ArrayList<>();
		for (UserDTO userDTO : groupDTO.getMembers()) {
			MyUser member = userDTOConversion.convertDTOtoEntities(userDTO);
			newMembers.add(member);
		}
		return newMembers;
	}



}
