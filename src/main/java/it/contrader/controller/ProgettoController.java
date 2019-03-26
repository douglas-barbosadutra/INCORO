package it.contrader.controller;

import java.util.List;

import it.contrader.dto.ProgettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Progetto;
import it.contrader.service.ProgettoService;

public class ProgettoController implements Controller {

    private static String sub_package = "progetto.";
	private ProgettoService progettoService;
	private Request request;

	public ProgettoController() {
		this.progettoService = new ProgettoService();
	}

	public List<Progetto> getAllProgetto() {
		return this.progettoService.getAllProgetto();
	}

	public ProgettoDTO readProgetto(int idprogetto) {
		return this.progettoService.readProgetto(idprogetto);
	}

	public boolean insertProgetto(ProgettoDTO progettoDTO) {
		return this.progettoService.insertProgetto(progettoDTO);
	}

	public boolean updateProgetto(ProgettoDTO progettoDTO) {
		return this.progettoService.updateProgetto(progettoDTO);
	}

	public boolean deleteProgetto(Integer idprogetto) {
		return this.progettoService.deleteProgetto(idprogetto);
	}

	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");

		if (mode == "menu") {
			MainDispatcher.getInstance().callView("Progetto", null);
		} else {
			switch (choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ProgettoRead", null);
				break;
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ProgettoInsert", null);
				break;
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ProgettoUpdate", null);
				break;
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ProgettoDelete", null);
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