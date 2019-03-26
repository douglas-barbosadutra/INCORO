package it.contrader.service;

import java.util.List;
import it.contrader.converter.ConverterProgetto;
import it.contrader.dao.ProgettoDAO;
import it.contrader.dto.ProgettoDTO;
import it.contrader.model.Progetto;

public class ProgettoService {
	
	
private ProgettoDAO progettoDAO;

		public ProgettoService() {
			this.progettoDAO = new ProgettoDAO();
		}

		public List<Progetto> getAllProgetto() {
			return this.progettoDAO.getAllProgetto();
		}

		public boolean insertProgetto(ProgettoDTO progettoDTO) {
			return this.progettoDAO.insertProgetto(ConverterProgetto.toEntity(progettoDTO));
		}
		
		public ProgettoDTO readProgetto(int idprogetto) {
			return ConverterProgetto.toDTO(this.progettoDAO.readProgetto(idprogetto));
		}
		
		public boolean updateProgetto(ProgettoDTO progettoDTO) {
			return this.progettoDAO.updateProgetto(ConverterProgetto.toEntity(progettoDTO));
		}
		
		public boolean deleteProgetto(int idprogetto) {
			return this.progettoDAO.deleteProgetto(idprogetto);
		}
		
		
	}

