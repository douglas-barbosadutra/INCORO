package it.contrader.dao;

import it.contrader.model.Keyword;
import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository <Keyword, Integer> {
	public Keyword findKeywordByIdKeyword(int idKeyword);
}
