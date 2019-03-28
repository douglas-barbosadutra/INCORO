package it.contrader.controller;

import java.util.List;

import it.contrader.dto.thingsDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Things;
import it.contrader.service.ThingsService;

public class ThingsController implements Controller {

	
	private static String sub_package = "thing.";
	private ThingsService thingService;
	private Request request;

	public ThingsController() {
		this.thingService = new ThingsService();
	}

	public List<Things> getAllUser() {
		return this.thingService.getAllUser();
	}

	public thingsDTO readUser(int thingId) {
		return this.thingService.readThings(thingId);
	}

	public boolean insertThings(thingsDTO thingsDTO) {
		return this.thingService.insertThings(thingsDTO);
	}

	public boolean updateThings(thingsDTO thingsDTO) {
		return this.thingService.updateThings(thingsDTO);
	}

	public boolean deleteThings(Integer thingsId) {
		return this.thingService.deleteThings(thingsId);
	}

	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");

		if (mode == "menu") {
			MainDispatcher.getInstance().callView("Things", null);
		} else {
			switch (choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ThingsRead", null);
				break;
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ThingsInsert", null);
				break;
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ThingsUpdate", null);
				break;
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ThingsDelete", null);
				break;
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
			default:
				MainDispatcher.getInstance().callView("Login", null);
				break;
			}
		}
	}

}
