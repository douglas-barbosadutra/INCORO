package it.contrader.service;
import java.util.ArrayList;
import java.util.List;

import it.contrader.converter.ThingsConverter;
import it.contrader.dao.ThingsDAO;
import it.contrader.dto.ThingsDTO;
import it.contrader.model.Things;

public class ThingsServiceDTO {
	private final ThingsDAO ThingsDAO;

	public ThingsServiceDTO() {
		this.ThingsDAO = new ThingsDAO();
	}

	/**
	 * Come vediamo la lista recuperata è di tipo Esempio ma noi la convertiamo in EsempioDTO
	 * Invito chi fa i converter a fare un metodo per convertire direttamente la lista senza farli uno ad uno perchè è sporco e poco efficiente
	 */
	public List<ThingsDTO> getAllThings() {

		List<Things> list = ThingsDAO.getAllThings();
		List<ThingsDTO> listDTO = new ArrayList<>();

		for (Things Things : list) {
			listDTO.add(ThingsConverter.toDTO(Things));
		}
		return listDTO;
	}
	
	public boolean updateThings (ThingsDTO ThingsDTO) {
		return this.ThingsDAO.updateThings(ThingsConverter.toEntity(ThingsDTO));	
	}
	
	public boolean deleteThings (ThingsDTO ThingsDTO) {
		return this.ThingsDAO.deleteThings(ThingsConverter.toEntity(ThingsDTO));	
	}
	
	public boolean insertThings (ThingsDTO ThingsDTO) {
		return this.ThingsDAO.insertThings(ThingsConverter.toEntity(ThingsDTO));	
	}

	public boolean insertCode (ThingsDTO ThingsDTO) {
		return this.ThingsDAO.insertCode(ThingsConverter.toEntityCode(ThingsDTO));	
	}
}