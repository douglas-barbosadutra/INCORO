package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.pattern.Converter;
import it.contrader.converter.ConverterKeyword;
import it.contrader.converter.ConverterLinkTK;
import it.contrader.converter.ConverterThing;
import it.contrader.dao.LinkTKRepository;

import it.contrader.dto.ThingDTO;
import it.contrader.dto.KeywordDTO;
import it.contrader.dto.LinkTKDTO;

import it.contrader.model.LinkTK;
import it.contrader.model.Keyword;
import it.contrader.model.Thing;


@Service
public class LinkTKService {
	private final LinkTKRepository linkTKRepository;
	
	@Autowired
	public LinkTKService(LinkTKRepository linkTKRepository) {
		this.linkTKRepository = linkTKRepository;
	}
	
	public List<LinkTKDTO> getListLinkTKDTO() {
		return ConverterLinkTK.toListDTO ((List<LinkTK>)linkTKRepository.findAll());
	}
	
	public LinkTKDTO insertLinkTK(LinkTKDTO linkTKDTO) {
		LinkTK link = ConverterLinkTK.toEntity(linkTKDTO);
		linkTKRepository.save(link); 
		return ConverterLinkTK.toDTO(link);
	}
	
	public boolean updateLinkTK(LinkTKDTO linkTKDTO) {
		return linkTKRepository.save(ConverterLinkTK.toEntity(linkTKDTO)) != null;
	}
	
	public boolean deleteLinkTK(int id) {
		this.linkTKRepository.deleteById(id);
		return true;
	}
	
	
	public List<LinkTKDTO> getAllByKeyword(KeywordDTO key){
		return ConverterLinkTK.toListDTO((List<LinkTK>) linkTKRepository.findAllByKeyword(ConverterKeyword.toEntity(key)));
	}
	
	public List<LinkTKDTO> getAllByThing(Thing thing){
		return ConverterLinkTK.toListDTO((List<LinkTK>) linkTKRepository.findAllByThing(thing));
	}
	
	
	/*
	public LinkTKDTO getLinkTKDTOById(int id) {
		return ConverterLinkTK.toDTO(linkTKRepository.findLinkTKByIdLinkTK(id));
	}*/
}
