package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.LabelDTO;
import it.contrader.model.Label;

public class ConverterLabel {
	
	public static LabelDTO toDto(Label label) {
		LabelDTO labelDTO = null;
		if(label != null) {
			labelDTO = new LabelDTO();
			labelDTO.setIdLabel(label.getIdLabel());
			labelDTO.setName(label.getName());
			labelDTO.setIdUser(label.getIdUser());	
		}
		return labelDTO;		
	}
	
	public static Label toEntity(LabelDTO labelDTO) {
		Label label = null;
		if(labelDTO != null) {
			label = new Label();
			label.setIdLabel(labelDTO.getIdLabel());
			label.setName(labelDTO.getName());
			label.setIdUser(labelDTO.getIdUser());
		}
		return label;
	}

	public static List<LabelDTO> toListDTO(List<Label> list){
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Label label : list) {
				listLabelDTO.add(ConverterLabel.toDto(label));
			}
		}
		return listLabelDTO;
	}
	
	public static List<Label> toListEntity(List<LabelDTO> listLabelDTO){
		List<Label> listLabel = new ArrayList<>();
		if (!listLabelDTO.isEmpty()) {
			for(LabelDTO labelDTO : listLabelDTO) {
				listLabel.add(ConverterLabel.toEntity(labelDTO));
			}
		}
		return listLabel;
	}
}