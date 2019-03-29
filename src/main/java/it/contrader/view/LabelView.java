package it.contrader.view;

import java.util.List;
import java.util.Scanner;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Label;
import it.contrader.model.User;

public class LabelView implements View {
	
	private LabelController labelController;
	private Request request;
	private String choice;
	private int idUserLocale ;
	
	public LabelView() {

		this.labelController = new LabelController();	
	}

	public void showResults(Request request) {
		// TODO Auto-generated method stub
		this.idUserLocale = Integer.parseInt(request.get("idUser").toString());

	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub

		System.out.println("\n------ Gestione label possedute -------\n");
		
		System.out.println("ID\\nome");
		System.out.print("------------------------------------------------------");
		System.out.println(idUserLocale);
		List<Label> labels = labelController.getLabelByUser(idUserLocale);
		
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
		request.put("idUser", idUserLocale);
	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		MainDispatcher.getInstance().callAction("Label", "doControl", this.request);
	}

}
