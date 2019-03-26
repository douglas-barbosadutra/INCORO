package it.contrader.view.user;

import java.util.Scanner;

import it.contrader.controller.ProgettoController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.dto.ProgettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class ProgettoUpdateView implements View {

	private ProgettoController progettoController;
	private Request request;

	public ProgettoUpdateView() {
		this.progettoController = new ProgettoController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int idprogettoToUpdate;
		String nomeprogetto;

		/*
		 * List<Progetto> progetto; Integer idprogetto; progetto = progettoController.getAllProgetto();
		 * 
		 */
		System.out.println("\n----- Seleziona il progetto da modificare  -----\n");
		// System.out.println();
		// progetto.forEach(us_type -> System.out.println(us_type.toString()));
		// System.out.println();
		ProgettoDTO progettoDTO = new ProgettoDTO();

		System.out.println("Digita l'Id del progetto da modificare:");
		try {
			idprogettoToUpdate = Integer.parseInt(getInput());
			if (idprogettoToUpdate != 0) {
				progettoDTO.setIdProgetto(idprogettoToUpdate);

				System.out.println("Digita il nuovo nomeprogetto:");
				nomeprogetto = getInput();
				if (!nomeprogetto.equals(""))
					progettoDTO.setNomeProgetto(nomeprogetto);

				progettoController.updateProgetto(progettoDTO);

			}
		} catch (Exception e) {
			System.out.println("Hai inserito un valore errato");
		}

	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().trim();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("mode", "menu");
		request.put("choice", "");
		MainDispatcher.getInstance().callAction("Progetto", "doControl", request);
	}

}


