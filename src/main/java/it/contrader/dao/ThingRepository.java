package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Thing;
import it.contrader.model.User;

import java.util.List;

public interface ThingRepository extends CrudRepository <Thing, Integer> {
	public Thing findThingByIdThing(int idThing);
	public List<Thing> findAllByName(String name);
	public List<Thing> findAllByUser(User u);
}