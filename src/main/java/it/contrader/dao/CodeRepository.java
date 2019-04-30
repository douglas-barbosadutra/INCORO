package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Code;

import java.util.List;

public interface CodeRepository extends CrudRepository <Code, Integer>{
	public Code findCodeByIdCode(int idCode);
	public List<Code> findAllByName(String name);
}