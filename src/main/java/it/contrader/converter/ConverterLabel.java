package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.LabelDTO;
import it.contrader.model.Label;

public class ConverterLabel {

	public static LabelDTO toDTO(Label Label) {
		LabelDTO LabelDTO = null;
		if (Label != null) {
			LabelDTO = new LabelDTO();
			LabelDTO.setLabelId(Label.getLabelId());
			LabelDTO.setLabelname(Label.getLabelname());

		}
		return LabelDTO;
	}

	public static Label toEntity(LabelDTO LabelDTO) {
		Label Label = null;
		if (LabelDTO != null) {
			Label = new Label();
			Label.setLabelId(LabelDTO.getLabelId());
			Label.setLabelname(LabelDTO.getLabelname());
			
		}
		return Label;
	}

	public static List<LabelDTO> toListDTO(List<Label> list) {
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Label Label : list) {
				listLabelDTO.add(ConverterLabel.toDTO(Label));
			}
		}
		return listLabelDTO;
	}

	public static List<Label> toListEntity(List<LabelDTO> listLabelDTO) {
		List<Label> list = new ArrayList<>();
		if (!listLabelDTO.isEmpty()) {
			for (LabelDTO LabelDTO : listLabelDTO) {
				list.add(ConverterLabel.toEntity(LabelDTO));
			}
		}
		return list;
	}
}

