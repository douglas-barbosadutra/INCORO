package it.contrader.converter;

import it.contrader.dto.LabelsDTO;
import it.contrader.model.Labels;

public class LabelsConverter {
	/*
	 * Converte un NodesDTO in Nodes
	 */
	public static Labels toEntity(LabelsDTO LabelsDTO) {

		Labels Labels = null;
		if (LabelsDTO != null) {
			Labels = new Labels(LabelsDTO.getId(), LabelsDTO.getName(), LabelsDTO.getFktouser());
		}
		return Labels;
	}

	/**
	 * Converte un Nodes in NodesDTO
	 */
	
	public static LabelsDTO toDTO(Labels Labels) {
		LabelsDTO LabelsDTO = null;
		if (Labels != null) {
			LabelsDTO = new LabelsDTO(Labels.getId(), Labels.getName(), Labels.getFktouser());
		}
		return LabelsDTO;
	}
}