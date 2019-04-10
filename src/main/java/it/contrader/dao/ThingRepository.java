package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Thing;

import java.util.List;

public interface ThingRepository extends CrudRepository <Thing, Integer> {
	//public Thing findThingById(Integer id);
	public List<Thing> findAllByName(String name);
}
