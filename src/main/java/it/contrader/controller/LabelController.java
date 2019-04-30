package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.converter.ConverterUser;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.LabelService;
import it.contrader.services.UserService;

import java.util.List;

@Controller
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
	
	private void visualLabel(HttpServletRequest request) {
		List<LabelDTO> allLabel = this.labelService.findLabelbyUser(idUser);
		request.getSession().setAttribute("allLabel", allLabel);
	}
	
	@RequestMapping(value = "/labelManagement", method = RequestMethod.GET)
	public String labelManagement(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		visualLabel(request);
		return "showLabel";
	}
	
	@RequestMapping(value = "/indietro", method = RequestMethod.GET)
	public String indietro(HttpServletRequest request) {	
		visualLabel(request);
		return "homeBO";
	}

	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("idLabel"));
		this.labelService.deleteLabelById(id);
		visualLabel(request);
		return "showLabel";
	}
	
	@RequestMapping(value = "/crea", method = RequestMethod.GET)
	public String insert(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		visualLabel(request);
		return "creaLabel";
	}
	
	@RequestMapping(value="/creaLabel", method = RequestMethod.POST)
	private String insertLabel(HttpServletRequest request) {
		UserDTO userDTO = new UserDTO();
		userDTO = userService.getUserDTOById(idUser); // ok questa è interna
		String name = request.getParameter("name").toString();
		if (labelService.getLabelDTOByNameAndUser(name, ConverterUser.toEntity(userDTO)) == null) {
			LabelDTO labelObj = new LabelDTO(0, name, userDTO);
			labelService.insertLabel(labelObj);
			visualLabel(request);
			return "showLabel";
		} else {
			return "labelError";
		}
	}
	
	@RequestMapping(value = "/erroreLabel", method = RequestMethod.GET)
	public String logoutt(HttpServletRequest request) {
		return "creaLabel";
	}
	
	@RequestMapping(value = "/openUpdate", method = RequestMethod.GET)
	public String openUpdate (HttpServletRequest request) {
		idLabel=Integer.parseInt(request.getParameter("idLabel"));
		String nome = "";
		if(idLabel != 0){
            LabelDTO labelDTO = new LabelDTO();
            labelDTO = labelService.getLabelDTOById(idLabel);
            nome = labelDTO.getName();
            request.getSession().setAttribute("nome", nome);
        }
		//visualLabel(request);
		return "modificaLabel";	
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
	
	@RequestMapping(value = "/erroreLabelModifica", method = RequestMethod.GET)
	public String logoutUpdate(HttpServletRequest request) {
		return "modificaLabel";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request) {
		session.invalidate();
		return "index";	
	}
}