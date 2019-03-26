package it.contrader.service;

import java.util.List;

import it.contrader.converter.ConverterUtenteFinale;
import it.contrader.dao.UtenteFinaleDAO;
import it.contrader.dto.UtenteFinaleDTO;
import it.contrader.model.UtenteFinale;

public class UtenteFinaleService {

	private UtenteFinaleDAO utenteFinaleDAO;

	public UtenteFinaleService() {
		this.utenteFinaleDAO = new UtenteFinaleDAO();
	}

	public List<UtenteFinale> getAllUtenteFinale() {
		return this.utenteFinaleDAO.getAllUtenteFinale();
	}

	public boolean insertUtenteFinale(UtenteFinaleDTO utenteFinaleDTO) {
				return this.utenteFinaleDAO.insertUtenteFinale(ConverterUtenteFinale.toEntity(utenteFinaleDTO));
	}
	
	public UtenteFinaleDTO readUtenteFinale(String partitaIva) {
		return ConverterUtenteFinale.toDTO(this.utenteFinaleDAO.readUtenteFinale(partitaIva));
	}
	
	public boolean updateUtenteFinale(UtenteFinaleDTO utenteFinaleDTO) {
		return this.utenteFinaleDAO.updateUtenteFinale(ConverterUtenteFinale.toEntity(utenteFinaleDTO));
	}
	
	public boolean deleteUtenteFinale(String partitaIva) {
		return this.utenteFinaleDAO.deleteUtenteFinale(partitaIva);
	}
	
	
}
