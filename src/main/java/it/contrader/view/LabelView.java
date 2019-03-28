package it.contrader.view;

import java.util.List;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.model.Label;
import it.contrader.model.User;

public class LabelView implements View {
	
	private LabelController labelController;
	private Request request;
	private String choice;
	
	public void LabelView() {
		this.labelController = new LabelController();	}
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub

		System.out.println("\n------ Gestione label possedute -------\n");
		
		System.out.println("ID\nome");
		System.out.print("------------------------------------------------------");
		List<Label> labels = labelController.getAllLabel();
		System.out.println();
		labels.forEach(label -> System.out.println(labels.toString()));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub

	}

}
