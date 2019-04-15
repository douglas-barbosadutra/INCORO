package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.contrader.dao.LabelRepository;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.ThingService;
import it.contrader.services.LabelService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Thing")
public class ThingController {
	
	private final ThingService thingService;
	private final LabelService ls;
	private HttpSession session;
	private int idThing;
	private int idUser;
	
	@Autowired 
	public ThingController(ThingService thingService, LabelService ls) {
		this.thingService = thingService;
		this.ls=ls;
	}
	
	
	
	private void visualThing(HttpServletRequest request){
		List<ThingDTO> allThing = this.thingService.getThingDTOByIdUser(idUser);
		//System.out.println("lista things: " + allThing);
		request.getSession().setAttribute("allThing", allThing);
		
		
	}
	
	@RequestMapping(value = "/indietro", method = RequestMethod.GET)
	public String indietro(HttpServletRequest request) {
		
		visualThing(request);
		return "homeBO";
	}
	
	@RequestMapping(value = "/codeThing", method = RequestMethod.GET)
	public String code(HttpServletRequest request) {
		
		visualThing(request);
		return "homeBO";
	}
	
	@RequestMapping(value = "/showCode", method = RequestMethod.GET)
	public String codice(HttpServletRequest request) {
		ThingDTO thingById;
		thingById = this.thingService.getThingDTOById(Integer.parseInt(request.getParameter("id")));
		request.getSession().setAttribute("codice",thingById.getCode());
		
		return "readCode";
	}
		
	@RequestMapping(value ="/thingManagement", method = RequestMethod.GET)
	public String thingManagement(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		List<ThingDTO> allThing = this.thingService.getThingDTOByIdUser(idUser);
		//System.out.println("lista things: " + allThing);
		request.getSession().setAttribute("allThing", allThing);
		return "showThing";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("id", id);
		this.thingService.deleteThingById(id);
		visualThing(request);
		return "showThing";
	}
	
	@RequestMapping(value = "/crea", method = RequestMethod.GET)
	public String insert(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		
		List<LabelDTO> labelDTO = ls.findLabelbyUser(idUser);
		request.getSession().setAttribute("list", labelDTO);
	
		//visualThing(request);
		//request.setAttribute("option", "insert");
		return "creaThing";
	}
	
	@RequestMapping(value = "/creaThing", method = RequestMethod.POST)
	public String insertThing(
			@RequestParam MultipartFile image,
			@RequestParam String name,
			@RequestParam String code,
			@RequestParam String idLabel) throws IOException {
		String nLblNoEsc = idLabel.replaceAll("\\s+","");
		Integer rIdLabel = Integer.parseInt(nLblNoEsc);
		String imagePath = image.getOriginalFilename();
		final String fileName = getFileName(imagePath);
		final String path = new String ("c:\\webdata\\images\\");
		OutputStream out = null;
        byte barr[]= image.getBytes();
        try {
	        out = new FileOutputStream(new File(path + File.separator + fileName));
	            out.write(barr, 0, barr.length);
	    } catch (FileNotFoundException fne) {
	        
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	    }

        List<ThingDTO> work = thingService.getListThingDTO();
        int i = 0;
        for(ThingDTO x:work) {
        	i++;
        }
        String modXml = createXmlFromDataThings(i,code,path+fileName,idUser,rIdLabel,name);
        
        
        ThingDTO thingObj = new ThingDTO(0, code, path+fileName, name, modXml, idUser, rIdLabel);
		thingService.insertThing(thingObj);
		//visualThing(request);
		
		return "homeBO"; 		
	}
	
	
	private String createXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel,String name) {
		// TODO Auto-generated method stub
		String result = new String("<Thing>\n");
		String pt = new String("c:\\webdata\\xml\\"+name+".xml");
		result = result.concat("<id_thing>" + i.toString() + "</id_thing>\n");
		result =result.concat("<code>"+ code +"</code>\n");
		result =result.concat("<image>"+ string +"</image>\n");
		result =result.concat("<name>"+name+"</name>\n");
		result =result.concat("<xml>"+pt+"</xml>\n");
		result =result.concat("<id_label>"+ rIdLabel.toString()+"</id_label>\n");
		result =result.concat("<id_user>" + idUser2.toString()+"</id_user>\n");
		result =result.concat("</Thing>\n");
		try {
			Files.write(Paths.get(pt), result.getBytes());
		} catch (IOException e) {
			
		}
		
		return pt;
	}


	private String getFileName(String imagePath) {
	        
	        	String fn = imagePath.substring(0).trim().replace("\"", "");
	        	int slashPos = fn.lastIndexOf( '\\' );
	            if ( slashPos == -1 )
	              slashPos = fn.lastIndexOf( '/' );
	            return fn.substring( slashPos > 0 ? slashPos + 1 : 0 );
	}
	
	/*
	@  RequestMapping(value = "/creaThing", method = RequestMethod.POST)
	public String insertThing(HttpServletRequest request) {
		String code = request.getParameter("code").toString();
		String image = request.getParameter("image").toString();
		String name = request.getParameter("name").toString();
		String xml = request.getParameter("xml").toString();
		String nameLabelR = request.getParameter("idLabel");
		String nameLabel = nameLabelR.replaceAll("\\s+","");
		Integer idLabel = Integer.parseInt(nameLabel);
		//Integer idLabel = Integer.parseInt(request.getParameter("idLabel").toString());
		//Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		ThingDTO thingObj = new ThingDTO(0, code, image, name, xml, idUser, idLabel);
		thingService.insertThing(thingObj);
		//visualThing(request);
		return "homeBO";
	}
	*/
	
	
	
	
	@RequestMapping(value="/openUpdateThing")
	public String openUpdateThing(HttpServletRequest request) {
		//idUser = Integer.parseInt(request.getParameter("idUser"));
		idThing = Integer.parseInt(request.getParameter("id"));
		List<LabelDTO> labelDTO = ls.findLabelbyUser(idUser);
		request.getSession().setAttribute("list", labelDTO);
		
		if (idThing != 0) {
		ThingDTO thingDTO = thingService.getThingDTOById(idThing);
		String nome = thingDTO.getName();
		String code = thingDTO.getCode();
		String image= thingDTO.getImage();
		request.getSession().setAttribute("nome", nome);
		request.getSession().setAttribute("code", code);
		request.getSession().setAttribute("image", image);
	
		}
		return "thingUpdate";
	}
	
	
	
	
	
	@RequestMapping(value = "/updateThing", method = RequestMethod.POST)
	public String updateThing(
			@RequestParam MultipartFile image,
			@RequestParam String name,
			@RequestParam String code,
			@RequestParam String idLabel) throws IOException, SQLException {
		String nLblNoEsc = idLabel.replaceAll("\\s+","");
		Integer rIdLabel = Integer.parseInt(nLblNoEsc);
		String imagePath = image.getOriginalFilename();
		final String fileName = getFileName(imagePath);
		final String path = new String ("c:\\webdata\\images\\");
		OutputStream out = null;
        byte barr[]= image.getBytes();
        int read = 0;
        try {
	        out = new FileOutputStream(new File(path + File.separator + fileName));
	            out.write(barr, 0, barr.length);
	    } catch (FileNotFoundException fne) {
	        
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	    }
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/incorospring", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}


        String modXml = updateXmlFromDataThings(idThing,code,path+fileName,idUser,rIdLabel,name);     
        ThingDTO thing = new ThingDTO(idThing, code, path+fileName, name, modXml, idUser, rIdLabel);       
        thingService.updateThing(thing);	
		return "homeBO"; 	
	}
	
	
	private String updateXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel,String name) {
		// TODO Auto-generated method stub
		i=idThing;
		String result = new String("<Thing>\n");
		String pt = new String("c:\\webdata\\xml\\"+name+".xml");
		result = result.concat("<id_thing>" + i.toString() + "</id_thing>\n");
		result =result.concat("<code>"+ code +"</code>\n");
		result =result.concat("<image>"+ string +"</image>\n");
		result =result.concat("<name>"+name+"</name>\n");
		result =result.concat("<xml>"+pt+"</xml>\n");
		result =result.concat("<id_label>"+ rIdLabel.toString()+"</id_label>\n");
		result =result.concat("<id_user>" + idUser2.toString()+"</id_user>\n");
		result =result.concat("</Thing>\n");
		try {
			Files.write(Paths.get(pt), result.getBytes());
		} catch (IOException e) {
			
		}
		
		return pt;
	}
	
	
	
	
	
	/*@RequestMapping(value="/updateThing", method= RequestMethod.POST)
	public String updateThing(HttpServletRequest request) {
		String code = request.getParameter("code");
		String image = request.getParameter("image");
		String name = request.getParameter("name");
		String xml = request.getParameter("xml");
		String nameLabelR = request.getParameter("idLabel");
		String nameLabel = nameLabelR.replaceAll("\\s+","");
		Integer idLabel = Integer.parseInt(nameLabel);
		
		ThingDTO thingDTO = new ThingDTO(idThing, code, image, name, xml, idUser,idLabel);
		thingService.insertThing(thingDTO);
		return "homeBO";
	}
	*/
	@RequestMapping(value = "/cercaThing", method = RequestMethod.GET)
	public String cercaThing(HttpServletRequest request) {
		final String content = request.getParameter("search");
		List<ThingDTO> allThing = this.thingService.findThingDTOByName(content);
		request.setAttribute("allThingDTO", allThing);
		return "homeThing";
	}
}