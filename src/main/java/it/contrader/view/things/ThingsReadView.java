package it.contrader.view.things;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import it.contrader.controller.Request;
import it.contrader.controller.ThingsController;
import it.contrader.dto.thingsDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Things;
import it.contrader.view.View;

public class ThingsReadView implements View {

	private ThingsController thingsController;
	private Request request;

	public ThingsReadView() {
		this.thingsController = new ThingsController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int thingsIdToRead;

		System.out.println("Inserisci l'ID della things:");

		try {
			thingsIdToRead = Integer.parseInt(getInput());
			thingsDTO thingsDTO = thingsController.readThings(thingsIdToRead);

			System.out.println("Id: " + thingsDTO.getIdthing());
			System.out.println("Nome: " + thingsDTO.getNome());
			
			//Wait user to show
			System.out.println("Premi un tasto per continuare");
			try {
				getInput();
			} catch (Exception e) {
				
			}

		} catch (Exception e) {
			System.out.println("Valore inserito errato.");
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