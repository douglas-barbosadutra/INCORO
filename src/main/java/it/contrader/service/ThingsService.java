package it.contrader.service;

import java.util.List;

import it.contrader.converter.ConverterThings;
import it.contrader.dao.ThingsDAO;
import it.contrader.dto.thingsDTO;
import it.contrader.model.Things;

public class ThingsService {

	private ThingsDAO thingsDAO;

	public ThingsService() {
		this.thingsDAO = new ThingsDAO();
	}

	public List<Things> getAllThings() {
		return this.thingsDAO.getAllThings();
	}

	public boolean insertThings(thingsDTO thingsDTO) {
		return this.thingsDAO.insertThing(ConverterThings.toEntity(thingsDTO));
	}
	
	public thingsDTO readThings(int idThing) {
		return ConverterThings.toDTO(this.thingsDAO.readThings(idThing));
	}
	
	public boolean updateThings(thingsDTO thingsDTO) {
		return this.thingsDAO.updateThings(ConverterThings.toEntity(thingsDTO));
	}
	
	public boolean deleteThings(int idThing) {
		return this.thingsDAO.deleteThings(idThing);
	}	
}