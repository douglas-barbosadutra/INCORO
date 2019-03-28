package it.contrader.view.things;
import java.util.Scanner;

import it.contrader.controller.Request;
import it.contrader.controller.ThingsController;
import it.contrader.dto.thingsDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class ThingsInsertView implements View {

	private ThingsController thingsController;
	private Request request;

	public ThingsInsertView() {
		this.thingsController = new ThingsController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		String name;
		System.out.println("Inserisci il nome della things:");
		System.out.println("Digita il nome: ");
		name = getInput();
		if (!name.equals("") ) {
			thingsController.insertThings(new thingsDTO(name));
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