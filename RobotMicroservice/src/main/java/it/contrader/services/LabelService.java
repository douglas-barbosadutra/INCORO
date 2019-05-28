package it.contrader.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterLabel;
import it.contrader.dao.LabelRepository;
import it.contrader.dto.LabelDTO;
import it.contrader.model.Label;

@Service
public class LabelService {
	private final LabelRepository labelRepository;
	
	@Autowired
	public LabelService(LabelRepository labelRepository) {
		this.labelRepository = labelRepository;
	}
	
	public LabelDTO insertLabel(LabelDTO labelDTO) {
		Label label = ConverterLabel.toEntity(labelDTO);
		labelRepository.save(label); 
		return ConverterLabel.toDto(label);
	}
		
	public boolean updateLabel(LabelDTO labelDTO) {
		return labelRepository.save(ConverterLabel.toEntity(labelDTO)) != null;
	}
	
	public boolean deleteLabel(int id) {
		labelRepository.deleteById(id);
		return true;
	}
	
	public List<LabelDTO> getAllLabel(){
		return ConverterLabel.toListDTO((List<Label>) labelRepository.findAll());
	}
	
	public List<LabelDTO> getListLabelDTO(){
		List<Label> listLabel = (List<Label>) labelRepository.findAll();
		List<LabelDTO> listLabelDTO = ConverterLabel.toListDTO(listLabel);
		return listLabelDTO;
	}
	
	public List<LabelDTO> getLabelDTOByIdUser(int idUser){
		List<Label> listLabel = (List<Label>) labelRepository.findAllByIdUser(idUser);
		List<LabelDTO> listLabelDTO = ConverterLabel.toListDTO(listLabel);
		return listLabelDTO;
	}
	
	public LabelDTO getLabelDTOById(int id) {
		Label label = labelRepository.findLabelByIdLabel(id);
		LabelDTO labelDTO = ConverterLabel.toDto(label);
		return labelDTO;
	}
	
	public LabelDTO findLabelDTOByIdUser(int idUser) {
		Label label = labelRepository.findLabelByIdUser(idUser);
		LabelDTO labelDTO = ConverterLabel.toDto(label);
		return labelDTO;
	}
	
	public LabelDTO getLabelDTOByNameAndIdUser(String name, int idUser) {
		Label label = labelRepository.findLabelByNameAndIdUser(name,idUser);
		LabelDTO labelDTO = ConverterLabel.toDto(label);
		return labelDTO;
	}
	
	public List<LabelDTO> findLabelDTOByName (String name){
		List<Label> listLabel = labelRepository.findAllByName(name);
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		for(Label label : listLabel) {
			listLabelDTO.add(ConverterLabel.toDto(label));
		}
		return listLabelDTO;
	}
	
	public LabelDTO findLabelById(int idLabel) {
		LabelDTO labelDTO = ConverterLabel.toDto(labelRepository.findLabelByIdLabel(idLabel));
		return labelDTO;
	}
	
	public List<LabelDTO> findLabelbyIdUser(int idUser){
		List<LabelDTO> listLabelDTO = new ArrayList<>();
		List<Label> list = labelRepository.findAllByIdUser(idUser);
		for(Label label : list) {
			listLabelDTO.add(ConverterLabel.toDto(label));
		}
		return listLabelDTO;
	}
}