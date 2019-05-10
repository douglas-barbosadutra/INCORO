package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.HardwareDTO;
import it.contrader.services.HardwareService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Hardware")

public class HardwareController {
	private HardwareService hardwareService;
	
	@Autowired
	public HardwareController(HardwareService hs) {
		this.hardwareService = hs;
	}
	
	@RequestMapping(value="/insertHardware", method= RequestMethod.POST)
	public boolean insertHardware(@RequestBody HardwareDTO hardwareDTO) {
		return hardwareService.insertHardware(hardwareDTO);
	}
	
	@RequestMapping(value="/updateHardware", method= RequestMethod.PUT)
	public boolean updateHardware(@RequestBody HardwareDTO hardwareDTO) {
		return hardwareService.updateHardware(hardwareDTO);
	}
	
	@RequestMapping(value="/deleteHardware" , method= RequestMethod.DELETE)
	public boolean deleteHardware(@RequestParam(value="id") Integer id) {		
	 	return hardwareService.deleteHardware(id);
	}

	@RequestMapping(value="/showHardware", method= RequestMethod.GET)
	public List<HardwareDTO> showHardware(){
		return hardwareService.getAllHardware();
	}
}