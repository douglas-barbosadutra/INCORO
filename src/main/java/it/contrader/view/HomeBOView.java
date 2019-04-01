package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
//import sun.applet.Main;

import java.util.Scanner;

public class HomeBOView implements View {

    private String choice;
    private Integer idUser;
    
    public void showResults(Request request) {
    	System.out.print("Benvenuto in INCORO "+ request.get("nomeUtente").toString() + " ");
    	System.out.println("il tuo Id Utente � : " + request.get("idUser"));
    	
    	this.idUser = Integer.parseInt(request.get("idUser").toString());
    	//System.out.println("------" +idUser+"------");
    }

    public void showOptions() {
        System.out.println("-------MENU Home BO -------\n");
        System.out.println("Seleziona cosa vuoi gestire:");
        System.out.println("[T]hings [L]abels [E]sci ");
        this.choice = this.getInput();
        // questo if a che serve?
        if (this.choice.equals("L")) 
        	this.choice = new String("Q");
    }

    public void submit() {
		if (choice.equalsIgnoreCase("T"))
        	MainDispatcher.getInstance().callView("Things", null);
            //MainDispatcher.getInstance().callAction("Client", "doControl", null);
        else if (choice.equalsIgnoreCase("L")) {
        	Request request = new Request();
        	request.put("idUser", this.idUser);
        	MainDispatcher.getInstance().callView("Label", request);
        	}
            //MainDispatcher.getInstance().callAction("Order", "doControl", null);
        else {
            Request request = new Request();
            request.put("choice", choice);
            MainDispatcher.getInstance().callAction("Login", "doControl", request);
        }
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}