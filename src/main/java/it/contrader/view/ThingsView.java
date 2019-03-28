package it.contrader.view;

import java.util.List;
import java.util.Scanner;

import it.contrader.controller.Request;
import it.contrader.controller.ThingsController;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Things;

public class ThingsView implements View {

	private ThingsController thingsController;
	private Request request;
	private String choice;
	
	public ThingsView() {
		this.thingsController = new ThingsController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		
		System.out.println("\n------ Gestione things -------\n");
		
		System.out.println("ID\tUsername\tPassword\tTipoUtente");
		System.out.print("------------------------------------------------------");
		List<Things> things = thingsController.getAllUser();
		System.out.println();
		things.forEach(thing -> System.out.println(thing.toString()));
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
		    MainDispatcher.getInstance().callAction("Things", "doControl", this.request);
	}
}