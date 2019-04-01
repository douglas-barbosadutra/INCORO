package it.contrader.service;

import java.util.List;

import it.contrader.converter.ConverterLabel;
import it.contrader.dao.LabelDAO;
import it.contrader.dto.LabelDTO;
import it.contrader.model.Label;

public class LabelService {

	private LabelDAO LabelDAO;

	public LabelService() {
		this.LabelDAO = new LabelDAO();
	}

	public List<Label> getAllLabel() {
		return this.LabelDAO.getAllLabel();
	}

	public boolean insertLabel(LabelDTO LabelDTO) {
		return this.LabelDAO.insertLabel(ConverterLabel.toEntity(LabelDTO));
	}
	
	public LabelDTO readLabel(int idLabel) {
		return ConverterLabel.toDTO(this.LabelDAO.readLabel(idLabel));
	}
	
	public boolean updateLabel(LabelDTO LabelDTO) {
		return this.LabelDAO.updateLabel(ConverterLabel.toEntity(LabelDTO));
	}

	public boolean deleteLabel(Integer labelsId) {
		// TODO Auto-generated method stub
		return this.LabelDAO.deletelabel(labelsId);
	}

	/*
	public List<Label> getLabelByUser(int idUser) {
		// TODO Auto-generated method stub
		return this.LabelDAO.getLabelByUser(idUser);
	}*/
}