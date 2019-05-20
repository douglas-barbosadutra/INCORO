package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterCoop;
import it.contrader.converter.ConverterUser;
import it.contrader.dao.CoopRepository;
import it.contrader.dao.UserRepository;
import it.contrader.dto.CoopDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Coop;
import it.contrader.model.User;

@Service
public class CoopService {
	private final CoopRepository coopRepository;

	@Autowired
	public CoopService(CoopRepository coopRepository) {
		this.coopRepository = coopRepository;
	}

	
	public List<CoopDTO> getAllCoop() {
		// TODO Auto-generated method stub
		return ConverterCoop.toListDTO( (List<Coop>) coopRepository.findAll());
	}

}
