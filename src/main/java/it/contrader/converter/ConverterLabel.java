package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Label;
import it.contrader.model.User;


public class ConverterLabel {
	
	public static LabelDTO convertToDto(Label label) {
		LabelDTO labelDTO = null;
		if(label != null) {
			labelDTO = new LabelDTO();
			labelDTO.setIdLabel(label.getIdLabel());
			labelDTO.setName(label.getName());
			
			UserDTO user = new UserDTO();
			user = ConverterUser.toDTO(label.getUser());
			labelDTO.setUser(user);
			
		}
		return labelDTO;		
	}
	
	public static Label convertToEntity(LabelDTO labelDTO) {
		Label label = null;
		if(labelDTO != null) {
			label = new Label();
			label.setIdLabel(labelDTO.getIdLabel());
			label.setName(labelDTO.getName());
			
			User user = new User();
			user = ConverterUser.toEntity(labelDTO.getUser());
			label.setUser(user);
			
		}
		return label;
	}

	public static List<LabelDTO> toListDTO(List<Label> list){
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Label label : list) {
				listLabelDTO.add(ConverterLabel.convertToDto(label));
			}
		}
		return listLabelDTO;
	}
	
	public static List<Label> toListEntity(List<LabelDTO> listLabelDTO){
		List<Label> listLabel = new ArrayList<>();
		if (!listLabelDTO.isEmpty()) {
			for(LabelDTO labelDTO : listLabelDTO) {
				listLabel.add(ConverterLabel.convertToEntity(labelDTO));
			}
		}
		return listLabel;
	}
}