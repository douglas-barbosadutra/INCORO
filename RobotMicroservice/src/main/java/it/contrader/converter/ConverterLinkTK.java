package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.LinkTKDTO;
import it.contrader.model.LinkTK;
import it.contrader.model.Thing;
import it.contrader.dto.ThingDTO;
import it.contrader.model.Keyword;
import it.contrader.dto.KeywordDTO;

public class ConverterLinkTK {
	public static LinkTKDTO toDTO(LinkTK link) {
		LinkTKDTO linkTKDTO = null;
		if(link != null) {
			linkTKDTO = new LinkTKDTO();
			linkTKDTO.setIdLinkTK(link.getIdLinkTK());
			
			ThingDTO thing = new ThingDTO();
			thing = ConverterThing.toDTO(link.getThing());
			linkTKDTO.setThing(thing);
		
			KeywordDTO keyword = new KeywordDTO();
			keyword = ConverterKeyword.toDTO(link.getKeyword());
			linkTKDTO.setKeyword(keyword);
		}
		return linkTKDTO;
	}
	
	public static LinkTK toEntity(LinkTKDTO linkDTO) {
		LinkTK link = null;
		if(linkDTO != null) { 
			link = new LinkTK();
			link.setIdLinkTK(linkDTO.getIdLinkTK());
			
			Thing thing = new Thing();
			thing = ConverterThing.toEntity(linkDTO.getThing());
			
			Keyword keyword = new Keyword();
			keyword = ConverterKeyword.toEntity(linkDTO.getKeyword());
			
			link.setThing(thing);
			link.setKeyword(keyword);
		
		}
		return link;
	}
	
	public static List<LinkTKDTO> toListDTO(List<LinkTK> list) {
		List<LinkTKDTO> listLinkDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (LinkTK link : list) {
				listLinkDTO.add(ConverterLinkTK.toDTO(link));
			}
		}
		return listLinkDTO;
	}
	
	public static List<LinkTK> toListEntity(List<LinkTKDTO> listLinkTKDTO) {
		List<LinkTK> list = new ArrayList<>();
		if (!listLinkTKDTO.isEmpty()) {
			for (LinkTKDTO linkTKDTO : listLinkTKDTO) {
				list.add(ConverterLinkTK.toEntity(linkTKDTO));
			}
		}
		return list;
	}	
}
