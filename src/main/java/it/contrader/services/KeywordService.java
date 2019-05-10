package it.contrader.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterKeyword;

import it.contrader.dao.KeywordRepository;
import it.contrader.dto.KeywordDTO;

import it.contrader.model.Keyword;


@Service
public class KeywordService {
	private KeywordRepository keywordRepository;
	
	@Autowired
	public KeywordService(KeywordRepository keywordRepository) {
		this.keywordRepository = keywordRepository;
	}

	public List<KeywordDTO> getAllKeyword() {
		return ConverterKeyword.toListDTO((List<Keyword>)keywordRepository.findAll());
	}

	public KeywordDTO getKeywordById(int id) {
		return ConverterKeyword.toDTO(keywordRepository.findKeywordByIdKeyword(id));
	}

	public boolean insertKeyword(KeywordDTO keywordDTO) {
		return keywordRepository.save(ConverterKeyword.toEntity(keywordDTO)) != null;
	}
	
	public boolean updateKeyword(KeywordDTO keywordDTO) {
		return keywordRepository.save(ConverterKeyword.toEntity(keywordDTO)) != null;
	}
	
	/*
	public void deleteKeywordById(Integer id) {
		keywordRepository.deleteById(id);
	}*/

	public boolean deleteKeyword(int id) {
		this.keywordRepository.deleteById(id);
		return true;
	}
}
