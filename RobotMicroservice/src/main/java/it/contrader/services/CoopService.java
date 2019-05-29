package it.contrader.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterCoop;
import it.contrader.dao.CoopRepository;
import it.contrader.dto.CoopDTO;
import it.contrader.model.Coop;


@Service
public class CoopService {
	private final CoopRepository coopRepository;

	@Autowired
	public CoopService(CoopRepository coopRepository) {
		this.coopRepository = coopRepository;
	}
	
	public List<CoopDTO> getAllCoop() {
		return ConverterCoop.toListDTO( (List<Coop>) coopRepository.findAll());
	}
}