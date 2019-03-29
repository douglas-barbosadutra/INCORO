package it.contrader.view.label;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.User;
import it.contrader.view.View;

public class LabelReadView implements View {

	private LabelController labelsController;
	private Request request;

	public LabelReadView() {
		this.labelsController = new LabelController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int userIdToRead;

		System.out.println("Inserisci l'ID della label:");

		try {
			userIdToRead = Integer.parseInt(getInput());
			LabelDTO userDB = labelsController.readLabel(userIdToRead);

			System.out.println("Id: " + userDB.getIdLabel());
			System.out.println("Username: " + userDB.getNomeLabel());
			
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
		MainDispatcher.getInstance().callAction("Label", "doControl", request);
	}

}
