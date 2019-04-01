package it.contrader.view.label;

import java.util.List;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Label;
import it.contrader.model.User;
import it.contrader.view.View;

public class LabelDeleteView implements View {

	private LabelController labelController;
	private Request request;

	public LabelDeleteView() {
		this.labelController = new LabelController();
	}

	@Override
	public void showResults(Request request) {
		}

	@Override
	public void showOptions() {
		//List<User> users;
		//String usersId;
		//users = userController.getAllUser();
		System.out.println("Seleziona l'ID label da cancellare : ");
		//System.out.println();
		//user.forEach(user -> System.out.println(user));
		//System.out.println();
		//System.out.println("Digita l'ID:");
		String labelId = getInput();
		if (labelId != null && StringUtils.isStrictlyNumeric(labelId)) {
			labelController.deleteLabel(Integer.parseInt(labelId));
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
		MainDispatcher.getInstance().callAction("Label", "doControl", request);
	}
}