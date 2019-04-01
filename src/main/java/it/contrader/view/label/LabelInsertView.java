package it.contrader.view.label;

import java.util.Scanner;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class LabelInsertView implements View {

	private LabelController labelController;
	private Request request;

	public LabelInsertView() {
		this.labelController = new LabelController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		String username;
		System.out.println("Inserisci i campi della label:");
		System.out.println("Digita la descrizione: ");
		username = getInput();
		System.out.println("Digita l' iduser proprietario);");
		int iduserL = Integer.parseInt(getInput());
		//System.out.println("Inserisci la tipologia utente");
		//usertype=getInput();
		
		if (!username.equals("") ) {
			LabelDTO wLabel = new LabelDTO();
			wLabel.setIdusers(iduserL);
			wLabel.setNome(username);
			labelController.insertLabel(wLabel);
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
