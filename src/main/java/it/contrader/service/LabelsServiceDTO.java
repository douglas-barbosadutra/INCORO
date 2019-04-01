package it.contrader.service;
import java.util.ArrayList;
import java.util.List;

import it.contrader.converter.LabelsConverter;
import it.contrader.dao.LabelsDAO;
import it.contrader.dto.LabelsDTO;
import it.contrader.model.Labels;



public class LabelsServiceDTO {
	private final LabelsDAO LabelsDAO;

	public LabelsServiceDTO() {
		this.LabelsDAO = new LabelsDAO();
	}

	/**
	 * Come vediamo la lista recuperata è di tipo Esempio ma noi la convertiamo in EsempioDTO
	 * Invito chi fa i converter a fare un metodo per convertire direttamente la lista senza farli uno ad uno perchè è sporco e poco efficiente
	 */
	public List<LabelsDTO> getAllLabels() {

		List<Labels> list = LabelsDAO.getAllLabels();
		List<LabelsDTO> listDTO = new ArrayList<>();

		for (Labels Labels : list) {
			listDTO.add(LabelsConverter.toDTO(Labels));
		}

		return listDTO;
	}
	
	

	public boolean updateLabels (LabelsDTO LabelsDTO) {
		return this.LabelsDAO.updateLabels(LabelsConverter.toEntity(LabelsDTO));
		
}
	
	public boolean deleteLabels (LabelsDTO LabelsDTO) {
		return this.LabelsDAO.deleteLabels(LabelsConverter.toEntity(LabelsDTO));
		
}
	
	public boolean insertLabels (LabelsDTO LabelsDTO) {
		return this.LabelsDAO.insertLabels(LabelsConverter.toEntity(LabelsDTO));
	
}
		
	
	
}



