package it.contrader.view.things;

import java.util.List;
import java.util.Scanner;

import it.contrader.controller.Request;
import it.contrader.controller.ThingsController;
import it.contrader.dto.thingsDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class ThingsUpdateView implements View {

	private ThingsController thingsController;
	private Request request;

	public ThingsUpdateView() {
		this.thingsController = new ThingsController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int thingsIdToUpdate;
		String nomeThings;
		System.out.println("\n----- Seleziona l'ID della things da modificate  -----\n");
		thingsDTO thingsDTO = new thingsDTO();
		System.out.println("Digita l'Id della things da modificare:");
		try {
			thingsIdToUpdate = Integer.parseInt(getInput());
			if (thingsIdToUpdate != 0) {
				thingsDTO.setIdthing(thingsIdToUpdate);
				System.out.println("Digita il nuovo nome della things:");
				nomeThings = getInput();
				if (!nomeThings.equals(""))
					thingsDTO.setNome(nomeThings);
				thingsController.updateThings(thingsDTO);
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
		MainDispatcher.getInstance().callAction("Things", "doControl", request);
	}
}