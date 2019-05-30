package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.ActionEvent;
import it.contrader.model.Label;
import it.contrader.model.Thing;
import java.util.List;

public interface ThingRepository extends CrudRepository <Thing, Integer> {
	public Thing findThingByIdThing(int idThing);
	public List<Thing> findAllByName(String name);
	public List<Thing> findAllByIdUser(int idUser);
	
	public List<Thing> findAllByLabel(int idLabel);
	public List<Thing> findAllByLabel(Label label);
	
}