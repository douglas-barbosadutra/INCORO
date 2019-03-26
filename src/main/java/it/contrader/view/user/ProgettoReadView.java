package it.contrader.view.user;

import java.util.Scanner;

import it.contrader.controller.ProgettoController;
import it.contrader.controller.Request;
import it.contrader.dto.ProgettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class ProgettoReadView implements View {

	private ProgettoController progettoController;
	private Request request;

	public ProgettoReadView() {
		this.progettoController = new ProgettoController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int idprogettoToRead;

		System.out.println("Inserisci l'ID del progetto:");

		try {
			idprogettoToRead = Integer.parseInt(getInput());
			ProgettoDTO progettoDB = progettoController.readProgetto(idprogettoToRead);

			System.out.println("Id progetto: " + progettoDB.getIdProgetto());
			System.out.println("Nome Progetto: " + progettoDB.getNomeProgetto());
			
			
			//Wait progetto to show
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
		MainDispatcher.getInstance().callAction("Progetto", "doControl", request);
	}

}


