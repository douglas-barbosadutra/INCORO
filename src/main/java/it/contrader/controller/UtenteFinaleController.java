package it.contrader.controller;

import java.util.List;

import it.contrader.dto.UtenteFinaleDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.UtenteFinale;
import it.contrader.service.UtenteFinaleService;

public class UtenteFinaleController implements Controller {

	
	private static String sub_package = "utenteFinale.";
	private UtenteFinaleService utenteFinaleService;
	private Request request;

	public UtenteFinaleController() {
		this.utenteFinaleService = new UtenteFinaleService();
	}

	public List<UtenteFinale> getAllUtenteFinale() {
		return this.utenteFinaleService.getAllUtenteFinale();
	}

	public UtenteFinaleDTO readUtenteFinale(String partitaIva) {
		return this.utenteFinaleService.readUtenteFinale(partitaIva);
	}

	public boolean insertUtenteFinale(UtenteFinaleDTO utenteFinaleDTO) {
		return this.utenteFinaleService.insertUtenteFinale(utenteFinaleDTO);
	}

	public boolean updateUtenteFinale(UtenteFinaleDTO utenteFinaleDTO) {
		return this.utenteFinaleService.updateUtenteFinale(utenteFinaleDTO);
	}

	public boolean deleteUtenteFinale(String partitaIva) {
		return this.utenteFinaleService.deleteUtenteFinale(partitaIva);
	}

	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");

		if (mode == "menu") {
			MainDispatcher.getInstance().callView("UtenteFinale", null);
		} else {
			switch (choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "UtenteFinaleRead", null);
				break;
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "UtenteFinaleInsert", null);
				break;
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "UtenteFinaleUpdate", null);
				break;
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "UtenteFinaleDelete", null);
				break;
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
			default:
				MainDispatcher.getInstance().callView("Login", null);
				break;
			}
		}
	}

}
