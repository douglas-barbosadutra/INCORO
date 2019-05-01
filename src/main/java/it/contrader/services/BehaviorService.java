package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.ArrayList;
//import java.util.List;

import it.contrader.converter.ConverterBehavior;

import it.contrader.dao.BehaviorRepository;

import it.contrader.dto.BehaviorDTO;

import it.contrader.model.Behavior;


@Service
public class BehaviorService {
	private BehaviorRepository behaviorRepository;
	
	@Autowired
	public BehaviorService(BehaviorRepository behaviorRepository) {
		this.behaviorRepository = behaviorRepository;
	}

	public List<BehaviorDTO> getListBehaviorDTO() {
		return ConverterBehavior.toListDTO((List<Behavior>)behaviorRepository.findAll());
	}

	public BehaviorDTO getBehaviorDTOById(int id) {
		return ConverterBehavior.toDTO(behaviorRepository.findBehaviorByIdBehavior(id));
	}

	public boolean insertBehavior(BehaviorDTO behaviorDTO) {
		return behaviorRepository.save(ConverterBehavior.toEntity(behaviorDTO)) != null;
	}

	public boolean updateBehavior(BehaviorDTO behaviorDTO) {
		return behaviorRepository.save(ConverterBehavior.toEntity(behaviorDTO)) != null;
	}
	
	public void deleteBehaviorById(Integer id) {
		behaviorRepository.deleteById(id);
	}

	public List<BehaviorDTO> findBehaviorDTOByName(String name) {
		final List<Behavior> list = behaviorRepository.findAllByName(name);
		final List<BehaviorDTO> behaviorDTOs = new ArrayList<>();
		list.forEach(i -> behaviorDTOs.add(ConverterBehavior.toDTO(i)));
		return behaviorDTOs;	
	}
}
