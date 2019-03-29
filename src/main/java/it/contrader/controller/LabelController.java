package it.contrader.controller;

import java.util.List;

import it.contrader.dto.LabelDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Label;
import it.contrader.model.Label;
import it.contrader.service.LabelService;

public class LabelController implements Controller {

	
	private static String sub_package = "label.";
	private LabelService labelsService;
	private Request request;

	public LabelController() {
		this.labelsService = new LabelService();
	}

	public List<Label> getAllLabel() {
		return this.labelsService.getAllLabel();
	}

	public LabelDTO readLabel(int LabelId) {
		return this.labelsService.readLabel(LabelId);
	}

	public boolean insertLabel(LabelDTO LabelsDTO) {
		return this.labelsService.insertLabel(LabelsDTO);
	}

	public boolean updateLabel(LabelDTO LabelsDTO) {
		return this.labelsService.updateLabel(LabelsDTO);
	}

	public boolean deleteLabel(Integer LabelsId) {
		return this.labelsService.deleteLabel(LabelsId);
	}
	public List<Label> getLabelByUser(int idUser) {
		return this.labelsService.getLabelByUser(idUser);
	}
 
	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");

		if (mode == "menu") {
			MainDispatcher.getInstance().callView("Label", null);
		} else {
			switch (choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "LabelRead", null);
				break;
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "LabelInsert", null);
				break;
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "LabelUpdate", null);
				break;
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "LabelDelete", null);
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
