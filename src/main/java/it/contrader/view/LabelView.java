package it.contrader.view;

import java.util.List;
import java.util.Scanner;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Label;

public class LabelView implements View {
	
	private LabelController labelController;
	private Request request;
	private String choice;
	
	private int idUserLocale ;
	
	public LabelView() {
		this.labelController = new LabelController();	
	}

	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		System.out.println("\n------ Gestione LABEL possedute -------\n");		
		System.out.println("ID\\t Descrizione \t ID utente");
		System.out.print("------------------------------------------------------");
		System.out.println(idUserLocale);
		List<Label> labels = labelController.getAllLabel();
		System.out.println();
		labels.forEach(label -> System.out.println(label.toString()));
		System.out.println();
		System.out.println("Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [E]sci");
		try {
			this.choice = getInput();
		} catch(Exception e) {
			this.choice = "";
		}
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "");
	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	@Override
	public void submit() {
		MainDispatcher.getInstance().callAction("Label", "doControl", this.request);
	}
}