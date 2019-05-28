package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.KeywordDTO;
import it.contrader.model.Keyword;

public class ConverterKeyword {
	
	public static KeywordDTO toDTO (Keyword keyword) {
		KeywordDTO keywordDTO = null;
		if(keyword != null) {
			keywordDTO = new KeywordDTO();
			keywordDTO.setIdKeyword(keyword.getIdKeyword());
			keywordDTO.setName(keyword.getName());
		}	
		return keywordDTO; 
	}
	
	public static Keyword toEntity(KeywordDTO keywordDTO) {
		Keyword keyword = null;
		if (keywordDTO != null) {
			keyword = new Keyword();
			keyword.setIdKeyword(keywordDTO.getIdKeyword());
			keyword.setName(keywordDTO.getName());
		}
		return keyword;
	}
	
	public static List<KeywordDTO> toListDTO(List<Keyword> list) {
		List<KeywordDTO> listWorksDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Keyword works : list) {
				listWorksDTO.add(ConverterKeyword.toDTO(works));
			}
		}
		return listWorksDTO;
	}
	
	public static List<Keyword> toListEntity(List<KeywordDTO> listKeywordDTO) {
		List<Keyword> list = new ArrayList<>();
		if (!listKeywordDTO.isEmpty()) {
			for (KeywordDTO KeywordDTO : listKeywordDTO) {
				list.add(ConverterKeyword.toEntity(KeywordDTO));
			}
		}
		return list;
	}	
}