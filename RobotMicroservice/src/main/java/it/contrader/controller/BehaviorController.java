package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.BehaviorDTO;
import it.contrader.services.BehaviorService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Behavior")

public class BehaviorController {
	private BehaviorService BehaviorService;
	
	@Autowired
	public BehaviorController(BehaviorService hs) {
		this.BehaviorService = hs;
	}
	
	@RequestMapping(value="/insertBehavior", method= RequestMethod.POST)
	public boolean insertBehavior(@RequestBody BehaviorDTO BehaviorDTO) {
		return BehaviorService.insertBehavior(BehaviorDTO);
	}
	
	@RequestMapping(value="/updateBehavior", method= RequestMethod.PUT)
	public boolean updateBehavior(@RequestBody BehaviorDTO BehaviorDTO) {
		return BehaviorService.updateBehavior(BehaviorDTO);
	}
	
	@RequestMapping(value="/deleteBeahavior" , method= RequestMethod.DELETE)
	public void delete(@RequestParam(value="idBehavior") BehaviorDTO BehaviorDTO) {		
		BehaviorService.deleteBehaviorById(BehaviorDTO.getIdBehavior());
	}

	@RequestMapping(value="/showBehaviors", method= RequestMethod.GET)
	public List<BehaviorDTO> showBehaviors(){
		return BehaviorService.getListBehaviorDTO();
	}
}