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
            
            //Change view according userType
            Integer userType= loginService.login(nomeUtente, password);
            if(userType==null)
                MainDispatcher.getInstance().callAction("Login", "doControl", request);
            
            if (userType.equals(new Integer(0)))
                MainDispatcher.getInstance().callView("HomeAdmin", request);
            
            if (userType.equals(new Integer(1))) {
            	Integer idUser = loginService.loginUser(nomeUtente, password);
            	request.put("idUser", idUser);
            	MainDispatcher.getInstance().callView("HomeBO", request);
            }
        }
        else MainDispatcher.getInstance().callView("Login", null);

    }
}
