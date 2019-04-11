package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.LabelDTO;
//import it.contrader.dto.UserDTO;
import it.contrader.model.Label;
import it.contrader.model.User;

public class ConverterLabel {
	
	public static Label convertToEntity(LabelDTO labeldto) {
		Label label = null;
		if(labeldto != null) {
			label = new Label();
			label.setIdLabel(labeldto.getIdLabel());
			label.setName(labeldto.getName());
			User user = new User();
			user.setIdUser(labeldto.getIdUser());   
			//dai al nuovo utente come chiave primaria quella che è chiave esterna su label			
			label.setUser(user);
		}
		return label;
	}

	public static LabelDTO convertToDto(Label label) {
		LabelDTO labeldto = null;
		if(label != null) {
			labeldto = new LabelDTO();
			labeldto.setIdLabel(label.getIdLabel());
			labeldto.setName(label.getName());	
			labeldto.setIdUser(label.getUser().getIdUser());
		}
		return labeldto;		
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