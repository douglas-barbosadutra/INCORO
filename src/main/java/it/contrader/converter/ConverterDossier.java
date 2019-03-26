package it.contrader.converter;


import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.DossierDTO;

import it.contrader.model.Dossier;





public class ConverterDossier {
	
	
		public static DossierDTO toDTO(Dossier dossier) {
			DossierDTO dossierDTO = null;
			if(dossier != null ) {
				dossierDTO = new DossierDTO();
				dossierDTO.setCostoDipendenti(dossier.getCostoDipendenti());
				dossierDTO.setFatturatoPeriodoDiImposta(dossier.getFatturatoPeriodoDiImposta());
				dossierDTO.setNumeroDipendenti(dossier.getNumeroDipendenti());
				dossierDTO.setCostiAttivitaRd(dossier.getCostiAttivitaRd());
				dossierDTO.setCostiPersonaleRd(dossier.getCostiPersonaleRd());
				dossierDTO.setPartitaIva(dossier.getPartitaIva());
				dossierDTO.setIdProgetto(dossier.getIdProgetto());
				dossierDTO.setIdDossier(dossier.getIdDossier());
				dossierDTO.setPeriodoDiImposta(dossier.getPeriodoDiImposta());
			}
			return dossierDTO;
		
			
		}
	
	public static Dossier toEntity(DossierDTO dossierDTO) {
		Dossier dossier = null;
		if(dossierDTO != null) {
			dossier = new Dossier();
			dossier.setCostoDipendenti(dossierDTO.getCostoDipendenti());
			dossier.setFatturatoPeriodoDiImposta(dossierDTO.getFatturatoPeriodoDiImposta());
			dossier.setNumeroDipendenti(dossierDTO.getNumeroDipendenti());
			dossier.setCostiAttivitaRd(dossierDTO.getCostiAttivitaRd());
			dossier.setCostiPersonaleRd(dossierDTO.getCostiPersonaleRd());
			dossier.setPartitaIva(dossierDTO.getPartitaIva());
			dossier.setIdProgetto(dossierDTO.getIdProgetto());
			dossier.setIdDossier(dossierDTO.getIdDossier());
			dossier.setPeriodoDiImposta(dossierDTO.getPeriodoDiImposta());
			}
		return dossier;
		
		
	}
	
	
	
	
	public static List<DossierDTO> toListDTO(List<Dossier> list) {
		List<DossierDTO> listDossierDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Dossier dossier : list) {
				listDossierDTO.add(ConverterDossier.toDTO(dossier));
			}
		}
		return listDossierDTO;
	}

	public static List<Dossier> toListEntity(List<DossierDTO> listDossierDTO) {
		List<Dossier> list = new ArrayList<>();
		if (!listDossierDTO.isEmpty()) {
			for (DossierDTO DossierDTO : listDossierDTO) {
				list.add(ConverterDossier.toEntity(DossierDTO));
			}
		}
		return list;
	}
	
	
	
	}


