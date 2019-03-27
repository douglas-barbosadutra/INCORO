package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
//import sun.applet.Main;

import java.util.Scanner;

public class HomeBOview implements View {

    private String choice;

    public void showResults(Request request) {
    	System.out.println("Benvenuto in INCORO "+request.get("nomeUtente").toString());
    }
//mod

    public void showOptions() {
        System.out.println("-------MENU-------\n");
        System.out.println("Seleziona cosa vuoi gestire:");
        System.out.println("[T]hings [L]abels [E]sci ");
        this.choice = this.getInput();
    }

    public void submit() {
        if (choice.equalsIgnoreCase("Y"))
        	MainDispatcher.getInstance().callView("Things", null);
            //MainDispatcher.getInstance().callAction("Client", "doControl", null);
        else if (choice.equalsIgnoreCase("L"))
        	MainDispatcher.getInstance().callView("Labels", null);
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