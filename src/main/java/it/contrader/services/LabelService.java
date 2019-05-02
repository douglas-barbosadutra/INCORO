package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterLabel;

import it.contrader.converter.ConverterUser;
import it.contrader.dao.LabelRepository;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Label;
import it.contrader.model.User;

@Service
public class LabelService {
	private final LabelRepository labelRepository;
	
	@Autowired
	public LabelService(LabelRepository labelRepository) {
		this.labelRepository = labelRepository;
	}
	
	public boolean insertLabel(LabelDTO labelDTO) {
		return labelRepository.save(ConverterLabel.convertToEntity(labelDTO)) != null;
	}
	
	public boolean updateLabel(LabelDTO labelDTO) {
		return labelRepository.save(ConverterLabel.convertToEntity(labelDTO)) != null;
	}
	
	public void deleteLabelById(int id) {
		labelRepository.deleteById(id);
	}
	
	public List<LabelDTO> getListLabelDTO(){
		List<Label> listLabel = (List<Label>) labelRepository.findAll();
		List<LabelDTO> listLabelDTO = ConverterLabel.toListDTO(listLabel);
		return listLabelDTO;
	}
	
	public List<LabelDTO> getLabelDTOByIdUser(int idUser){
		User u = new User();
		u.setIdUser(idUser);
		List<Label> listLabel = (List<Label>) labelRepository.findAllByUser(u);
		List<LabelDTO> listLabelDTO = ConverterLabel.toListDTO(listLabel);
		return listLabelDTO;
	}
	
	public LabelDTO getLabelDTOById(int id) {
		Label label = labelRepository.findLabelByIdLabel(id);
		LabelDTO labelDTO = ConverterLabel.convertToDto(label);
		return labelDTO;
	}
	
	public LabelDTO getLabelDTOByNameAndUser(String name, User user) {
		Label label = labelRepository.findLabelByNameAndUser(name,user);
		LabelDTO labelDTO = ConverterLabel.convertToDto(label);
		return labelDTO;
	}
	
	public List<LabelDTO> findLabelDTOByName (String name){
		List<Label> listLabel = labelRepository.findAllByName(name);
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		for(Label label : listLabel) {
			listLabelDTO.add(ConverterLabel.convertToDto(label));
		}
		return listLabelDTO;
	}
	
	public LabelDTO findLabelById(int idLabel) {
		LabelDTO labelDTO = ConverterLabel.convertToDto(labelRepository.findLabelByIdLabel(idLabel));
		return labelDTO;
	}
	
	public List<LabelDTO> findLabelbyUser(int idUser){
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(idUser);
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		List<Label> list = labelRepository.findAllByUser(ConverterUser.toEntity(userDTO));
		for(Label label : list) {
			listLabelDTO.add(ConverterLabel.convertToDto(label));
		}
		return listLabelDTO;
	}
}