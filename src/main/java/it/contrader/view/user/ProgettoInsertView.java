package it.contrader.view.user;

import java.util.Scanner;

import it.contrader.controller.ProgettoController;
import it.contrader.controller.Request;
import it.contrader.dto.ProgettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class ProgettoInsertView implements View {

	private ProgettoController progettoController;
	private Request request;

	public ProgettoInsertView() {
		this.progettoController = new ProgettoController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int idprogetto;
		String nomeprogetto;
     
		System.out.println("Inserisci i campi del progetto:");
		
		System.out.println("Digita l'ID del progetto: ");
		idprogetto = Integer.parseInt(getInput());
		System.out.println("Digita il nome del progetto: ");
		nomeprogetto = getInput();
		
		if (!nomeprogetto.equals("") ) {
			progettoController.insertProgetto(new ProgettoDTO(idprogetto, nomeprogetto));
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


