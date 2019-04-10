package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Thing;

public interface ThingDAO extends CrudRepository <Thing, Integer> 
{
	

}
