package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UtenteFinaleDTO;
import it.contrader.model.UtenteFinale;

public class ConverterUtenteFinale {

	public static UtenteFinaleDTO toDTO(UtenteFinale utenteFinale) {
		UtenteFinaleDTO utenteFinaleDTO = null;
		if (utenteFinale != null) {
			utenteFinaleDTO = new UtenteFinaleDTO();
			utenteFinaleDTO.setDenominazioneSocieta(utenteFinale.getDenominazioneSocieta());
			utenteFinaleDTO.setFormaGiuridica(utenteFinale.getFormaGiuridica());
			utenteFinaleDTO.setSedeLegale(utenteFinale.getSedeLegale());
			utenteFinaleDTO.setPartitaIva(utenteFinale.getPartitaIva());
			utenteFinaleDTO.setTelefono(utenteFinale.getTelefono());
			utenteFinaleDTO.setEmail(utenteFinale.getEmail());
			utenteFinaleDTO.setIndirizzoUnitaLocale(utenteFinale.getIndirizzoUnitaLocale());
			utenteFinaleDTO.setAttivitaAzienda(utenteFinale.getAttivitaAzienda());
			utenteFinaleDTO.setLegaleRappresentante(utenteFinale.getLegaleRappresentante());
			utenteFinaleDTO.setNatoA(utenteFinale.getNatoA());
			utenteFinaleDTO.setNatoIl(utenteFinale.getNatoIl());
			utenteFinaleDTO.setIdUtente(utenteFinale.getIdUtente());
		}
		return utenteFinaleDTO;
	}

	public static UtenteFinale toEntity(UtenteFinaleDTO utenteFinaleDTO) {
		UtenteFinale utenteFinale = null;
		if (utenteFinaleDTO != null) {
			utenteFinale = new UtenteFinale();
			utenteFinale.setDenominazioneSocieta(utenteFinaleDTO.getDenominazioneSocieta());
			utenteFinale.setFormaGiuridica(utenteFinaleDTO.getFormaGiuridica());
			utenteFinale.setSedeLegale(utenteFinaleDTO.getSedeLegale());
			utenteFinale.setPartitaIva(utenteFinaleDTO.getPartitaIva());
			utenteFinale.setTelefono(utenteFinaleDTO.getTelefono());
			utenteFinale.setEmail(utenteFinaleDTO.getEmail());
			utenteFinale.setIndirizzoUnitaLocale(utenteFinaleDTO.getIndirizzoUnitaLocale());
			utenteFinale.setAttivitaAzienda(utenteFinaleDTO.getAttivitaAzienda());
			utenteFinale.setLegaleRappresentante(utenteFinaleDTO.getLegaleRappresentante());
			utenteFinale.setNatoA(utenteFinaleDTO.getNatoA());
			utenteFinale.setNatoIl(utenteFinaleDTO.getNatoIl());
			utenteFinale.setIdUtente(utenteFinaleDTO.getIdUtente());
			
		}
		return utenteFinale;
	}

	public static List<UtenteFinaleDTO> toListDTO(List<UtenteFinale> list) {
		List<UtenteFinaleDTO> listUtenteFinaleDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (UtenteFinale utenteFinale : list) {
				listUtenteFinaleDTO.add(ConverterUtenteFinale.toDTO(utenteFinale));
			}
		}
		return listUtenteFinaleDTO;
	}

	public static List<UtenteFinale> toListEntity(List<UtenteFinaleDTO> listUtenteFinaleDTO) {
		List<UtenteFinale> list = new ArrayList<>();
		if (!listUtenteFinaleDTO.isEmpty()) {
			for (UtenteFinaleDTO utenteFinaleDTO : listUtenteFinaleDTO) {
				list.add(ConverterUtenteFinale.toEntity(utenteFinaleDTO));
			}
		}
		return list;
	}
}
