package it.contrader.view.label;

import java.util.List;
import java.util.Scanner;

import it.contrader.controller.LabelController;
import it.contrader.controller.Request;
import it.contrader.controller.UserController;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.View;

public class LabelUpdateView implements View {

	private LabelController labelsController;
	private Request request;

	public LabelUpdateView() {
		this.labelsController = new LabelController();
	}

	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		int userIdToUpdate;
		String nome;
		System.out.println("\n----- Seleziona l'ID della label da modificate  -----\n");
		// System.out.println();
		// users.forEach(us_type -> System.out.println(us_type.toString()));
		// System.out.println();
		LabelDTO labelDTO = new LabelDTO();

		System.out.println("Digita l'Id della label da modificare:");
		try {
			userIdToUpdate = Integer.parseInt(getInput());
			if (userIdToUpdate != 0) {
				labelDTO.setIdLabel(userIdToUpdate);

				System.out.println("Digita il nuovo nome:");
				nome = getInput();
				if (!nome.equals(""))
					labelDTO.setNome(nome);
				labelsController.updateLabel(labelDTO);
			}
		} catch (Exception e) {
			System.out.println("Hai inserito un valore errato");
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