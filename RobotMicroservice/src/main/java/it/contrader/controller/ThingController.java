package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ThingDTO;
import it.contrader.dto.LabelDTO;
import it.contrader.services.ThingService;
import it.contrader.services.LabelService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Thing")

public class ThingController {
	
	private final ThingService ts;
	private final LabelService ls;
	//private HttpSession session;
	private int idThing;
	private int idUser;
	
	@Autowired 
	public ThingController(ThingService ts, LabelService ls) {
		this.ts = ts;
		this.ls = ls;
	}
	
	// METODI DI REST CONTROLLER
	@RequestMapping(value="/insertThing", method= RequestMethod.POST)
	public ThingDTO insertThing(@RequestBody ThingDTO thing) {
		return ts.insertThing(thing);
	}
	
	@RequestMapping(value="/deleteThing" , method= RequestMethod.DELETE)
	public boolean deleteThing(@RequestParam(value="id") Integer id) {		
		return ts.deleteThing(id);
	}
	
	@RequestMapping(value="/showThing" , method= RequestMethod.GET)
	public List<ThingDTO> showThings() {		
		return ts.getAllThings();
	}
	
	@RequestMapping(value="/updateThing" , method= RequestMethod.PUT)
	public ThingDTO showThing(@RequestBody ThingDTO thing) {		
		return ts.insertThing(thing);
	}
	
	@RequestMapping(value="/findThing" , method= RequestMethod.GET)
	public ThingDTO findUser( @RequestBody ThingDTO thing) {		
		return ts.findThingById(thing.getIdThing());
	}
	
	@RequestMapping(value="/getAllThing", method= RequestMethod.GET)
	public List<ThingDTO> getAllThing(){
		return ts.getAllThings();
	}
		
	@RequestMapping(value ="/thingManagement", method = RequestMethod.GET)
	public String thingManagement(HttpServletRequest request) {
		idUser = Integer.parseInt(request.getParameter("idUser"));
		List<ThingDTO> allThing = this.ts.getThingDTOByIdUser(idUser);
		request.getSession().setAttribute("allThing", allThing);
		return "showThing";		
	}
	

	
	@RequestMapping(value = "/showCode", method = RequestMethod.GET)
	public String codice(HttpServletRequest request) {
		ThingDTO thingById;
		thingById = this.ts.getThingDTOById(Integer.parseInt(request.getParameter("id")));
		request.getSession().setAttribute("codice",thingById.getCode());
		return "readCode";
	}
	
	private String createXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel, String name) {
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
	
	private String updateXmlFromDataThings(Integer i,String code, String string, Integer idUser2, Integer rIdLabel,String name) {
		i=idThing;
		String result = new String("<Thing>\n");
		String pt = new String("c:\\webdata\\xml\\"+name+".xml");
		result = result.concat("<id_thing>" + i.toString() + "</id_thing>\n");
		result = result.concat("<code>"+ code +"</code>\n");
		result = result.concat("<image>"+ string +"</image>\n");
		result = result.concat("<name>"+name+"</name>\n");
		result = result.concat("<xml>"+pt+"</xml>\n");
		result = result.concat("<id_label>"+ rIdLabel.toString()+"</id_label>\n");
		result = result.concat("<id_user>" + idUser2.toString()+"</id_user>\n");
		result = result.concat("</Thing>\n");
		try {
			Files.write(Paths.get(pt), result.getBytes());
		} catch (IOException e) {
			
		}
		return pt;
	}
	
}


/*
@RequestMapping(value = "/cercaThing", method = RequestMethod.GET)
public String cercaThing(HttpServletRequest request) {
	final String content = request.getParameter("search");
	List<ThingDTO> allThing = this.ts.findThingDTOByName(content);
	request.setAttribute("allThingDTO", allThing);
	return "homeThing";
}*/

/*
private void visualThing(HttpServletRequest request){
	// il passaggio dell'utente avviene nel service
	List<ThingDTO> allThing = this.ts.getThingDTOByIdUser(idUser);
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
}*/

/* METODO DI CONTROLLER	
@RequestMapping(value = "/delete", method = RequestMethod.GET)
public String delete(HttpServletRequest request) {
	int id = Integer.parseInt(request.getParameter("id"));
	request.getSession().setAttribute("id", id);
	this.ts.deleteThingById(id);
	visualThing(request);
	return "showThing";
}*/

/*
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
	
	UserDTO user = new UserDTO();
	user = us.findUserDTOById(idUser);

	LabelDTO label = new LabelDTO();
	label =ls.findLabelById(rIdLabel);
	
    String modXml = updateXmlFromDataThings(idThing,code,path+fileName, idUser,rIdLabel,name);     
    ThingDTO thing = new ThingDTO(idThing, code, path+fileName, name, modXml, user, label);       
    ts.updateThing(thing);	
	return "homeBO"; 	
}*/

/*
@RequestMapping(value="/openUpdateThing")
public String openUpdateThing(HttpServletRequest request) {
	idThing = Integer.parseInt(request.getParameter("id"));
	List<LabelDTO> labelDTO = ls.findLabelbyUser(idUser);
	request.getSession().setAttribute("list", labelDTO);
	
	if (idThing != 0) {
		ThingDTO thingDTO = ts.getThingDTOById(idThing);
		String nome = thingDTO.getName();
		String code = thingDTO.getCode();
		String image= thingDTO.getImage();
		request.getSession().setAttribute("nome", nome);
		request.getSession().setAttribute("code", code);
		request.getSession().setAttribute("image", image);
	}
	return "thingUpdate";
}*/

/*
@RequestMapping(value = "/creaThing", method = RequestMethod.POST)
public String insertThing(
		@RequestParam MultipartFile image,
		@RequestParam String name,
		@RequestParam String code,
		@RequestParam String idLabel) throws IOException {
	String nLblNoEsc = idLabel.replaceAll("\\s+","");
	Integer rIdLabel = Integer.parseInt(nLblNoEsc);
	//questa è una puttanata
	String imagePath = image.getOriginalFilename(); // secondo me è ridontante con quello di sotto
	final String fileName = getFileName(imagePath); // method returns the last name of the pathname's name sequence
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

    // poi si crea una lista di thingDTO ma non viene mai usata
    List<ThingDTO> work = ts.getListThingDTO();
    int i = 0;
    	for(ThingDTO x : work) {
    		i++;
    	}
    String modXml = createXmlFromDataThings(i,code,path+fileName,idUser,rIdLabel,name);
    
    UserDTO user = new UserDTO();
	user = us.findUserDTOById(idUser);

	LabelDTO label = new LabelDTO();
	label =ls.findLabelById(rIdLabel);
	
    ThingDTO thingObj = new ThingDTO(0, code, path+fileName, name, modXml, user, label);
	ts.insertThing(thingObj);
	return "homeBO"; 		
}*/