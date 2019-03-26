package it.contrader.view.user;

import java.util.List;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import it.contrader.controller.Request;
import it.contrader.controller.ProgettoController;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Progetto;
import it.contrader.view.View;

public class ProgettoDeleteView implements View {
	

	

		private ProgettoController progettoController;
		private Request request;

		public ProgettoDeleteView() {
			this.progettoController = new ProgettoController();
		}

		@Override
		public void showResults(Request request) {
		}

		@Override
		public void showOptions() {
			//List<Progetto> progetto;
			//String idprogetto;

			//progetto = progettoController.getAllProgetto();
			System.out.println("Seleziona il progetto da cancellare : ");
			//System.out.println();
			//progetto.forEach(progetto -> System.out.println(progetto));
			//System.out.println();
			//System.out.println("Digita l'ID Progetto:");
			String idprogetto = getInput();

			if (idprogetto != null && StringUtils.isStrictlyNumeric(idprogetto)) {
				progettoController.deleteProgetto(Integer.parseInt(idprogetto));
				
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
			MainDispatcher.getInstance().callAction("Progetto", "doControl", request);
		}

	}


