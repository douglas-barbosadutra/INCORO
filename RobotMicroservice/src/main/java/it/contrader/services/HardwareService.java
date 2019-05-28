package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterHardware;
import it.contrader.dao.HardwareRepository;
import it.contrader.dto.HardwareDTO;
import it.contrader.model.Hardware;

@Service
public class HardwareService {
	private HardwareRepository hardwareRepository;
	
	@Autowired
	public HardwareService(HardwareRepository hardwareRepository) {
		this.hardwareRepository = hardwareRepository;
	}

	public List<HardwareDTO> getAllHardware() {
		return ConverterHardware.toListDTO((List<Hardware>)hardwareRepository.findAll());
	}

	public HardwareDTO getHardwareById(int id) {
		return ConverterHardware.toDTO(hardwareRepository.findHardwareByIdHardware(id));
	}

	public HardwareDTO insertHardware(HardwareDTO hardwareDTO) {
		Hardware hardware = ConverterHardware.toEntity(hardwareDTO);
		hardwareRepository.save(hardware); 
		return ConverterHardware.toDTO(hardware);
	}

	public boolean updateHardware(HardwareDTO hardwareDTO) {
		return hardwareRepository.save(ConverterHardware.toEntity(hardwareDTO)) != null;
	}
	
	public boolean deleteHardware(int id) {
		this.hardwareRepository.deleteById(id);
		return true;
	}
	
	public List<HardwareDTO> findHardwareDTOByName(String name) {
		final List<Hardware> list = hardwareRepository.findAllByName(name);
		final List<HardwareDTO> hardwareDTOs = new ArrayList<>();
		list.forEach(i -> hardwareDTOs.add(ConverterHardware.toDTO(i)));
		return hardwareDTOs;	
	}
}