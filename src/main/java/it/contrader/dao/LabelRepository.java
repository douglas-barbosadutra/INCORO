package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Label;
import it.contrader.model.Thing;
import it.contrader.model.User;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Integer> {

	public List<Label> findAllByUser(User u);
    public Label findByThing(Thing s);
    public List<Label> findAllByName (String name);
}