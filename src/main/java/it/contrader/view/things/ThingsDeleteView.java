package it.contrader.view.things;

import java.util.List;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import it.contrader.controller.Request;
import it.contrader.controller.ThingsController;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Things;
import it.contrader.view.View;

public class ThingsDeleteView implements View {

	private ThingsController thingsController;
	private Request request;

	public ThingsDeleteView() {
		this.thingsController = new ThingsController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		//List<User> users;
		//String usersId;

		//users = userController.getAllUser();
		System.out.println("Seleziona l'ID della things da cancellare : ");
		//System.out.println();
		//user.forEach(user -> System.out.println(user));
		//System.out.println();
		//System.out.println("Digita l'ID:");
		String thingsId = getInput();
		
		if (thingsId != null && StringUtils.isStrictlyNumeric(thingsId)) {
			thingsController.deleteThings(Integer.parseInt(thingsId));
			
		} else {
			System.out.println("Valore inserito errato");
		}
	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("mode", "menu");
		request.put("choice", "");
		MainDispatcher.getInstance().callAction("Things", "doControl", request);
	}

}
