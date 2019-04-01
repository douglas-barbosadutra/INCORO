package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.service.LoginService;

public class HomeController implements Controller {

    private LoginService loginService;

    public HomeController() {
        loginService = new LoginService();
    }

    public void doControl(Request request) {
        if (request != null) {
            String nomeUtente = request.get("nomeUtente").toString();
            String password = request.get("password").toString();
            
            // cambia vista in base al tipo 
            Integer userType = loginService.login(nomeUtente, password);
            if(userType==null)
                MainDispatcher.getInstance().callAction("Login", "doControl", request);
            
            // vista per il Super User
            if (userType.equals(new Integer(0)))
                MainDispatcher.getInstance().callView("HomeAdmin", request);
            
            // vista par il BO
            if (userType.equals(new Integer(1))) {
            	Integer idUser = loginService.loginUser(nomeUtente, password);
            	System.out.println("------" + idUser +"------");
            	request.put("idUser", idUser);
            	System.out.println("------" + request.get("idUser") +"------");
            	MainDispatcher.getInstance().callView("HomeBO", request);
            }
        }
        else MainDispatcher.getInstance().callView("Login", null);
    }
}