package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.converter.ConverterUser;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.LabelService;
import it.contrader.services.UserService;

import java.util.List;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Label")
public class LabelController {
	
	private final LabelService labelService;
	private final UserService userService;
	private HttpSession session;
	private int idLabel;
	private int idUser;
	
	@Autowired
	public LabelController(LabelService labelService, UserService userService) {
		this.labelService = labelService;
		this.userService = userService;
	}
	@RequestMapping(value="/showLabel" , method= RequestMethod.GET)
	public List<LabelDTO> showLabel() {		
		return labelService.getAllLabel();
	}
	
	private void visualLabel(HttpServletRequest request) {
		List<LabelDTO> allLabel = this.labelService.findLabelbyUser(idUser);
		request.getSession().setAttribute("allLabel", allLabel);
	}
	
	@RequestMapping(value = "/labelManagement", method = RequestMethod.GET)
	public List<LabelDTO> labelManagement(@RequestBody UserDTO userDto) {
		idUser = userDto.getIdUser();
		return this.labelService.findLabelbyUser(idUser);
	}
	
	/*
	@RequestMapping(value ="/deleteLabel", method = RequestMethod.DELETE)
	public boolean delete(@RequestBody LabelDTO labelDto) {
		int id = labelDto.getIdLabel();
		this.labelService.deleteLabelById(id);
		return true;
	}*/
	
	@RequestMapping(value="/deleteLabel" , method= RequestMethod.DELETE)
	public boolean deleteLabel(@RequestParam(value="id") Integer id) {		
		return labelService.deleteLabel(id);
	}
	
	/*
	@RequestMapping(value="/insertLabel", method = RequestMethod.PUT)
	private boolean insertLabel(@RequestBody LabelDTO labelDto) {
		String name = labelDto.getName();
		if (labelService.getLabelDTOByNameAndUser(name, ConverterUser.toEntity(labelDto.getUser())) == null) {
			LabelDTO labelObj = new LabelDTO(0, name, labelDto.getUser());
			labelService.insertLabel(labelObj);
			return true;
		} else {
			return false;
		}
	}*/
	
	@RequestMapping(value="/insertLabel", method= RequestMethod.POST)
	public LabelDTO insertLabel(@RequestBody LabelDTO label) {
		return labelService.insertLabel(label);
	}
	
	@RequestMapping(value = "/erroreLabel", method = RequestMethod.GET)
	public String logoutt(HttpServletRequest request) {
		return "creaLabel";
	}
	
	@RequestMapping(value = "/modifica", method = RequestMethod.GET)
	public String modifica(HttpServletRequest request) {
		LabelDTO labelOldDTO =new LabelDTO();
		UserDTO userDTO = new UserDTO();
		userDTO = userService.getUserDTOById(idUser); 
		labelOldDTO = labelService.getLabelDTOById(idLabel);
		String name = request.getParameter("name").toString();
		if (labelService.getLabelDTOByNameAndUser(name, ConverterUser.toEntity(userDTO)) == null || labelService.getLabelDTOByNameAndUser(name, ConverterUser.toEntity(userDTO)).equals(labelOldDTO)) {
				LabelDTO labelObj = new LabelDTO(idLabel, name, userDTO);
				labelService.updateLabel(labelObj);
				visualLabel(request);
				return "showLabel";
		}
		else {
			return "erroreLabelModifica";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request) {
		session.invalidate();
		return "index";	
	}
}