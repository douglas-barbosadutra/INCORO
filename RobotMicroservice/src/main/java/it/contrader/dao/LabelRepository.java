package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Label;
import it.contrader.model.Thing;


import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Integer> {

	public List<Label> findAllByIdUser(int idUser);
	public Label findLabelByIdUser(int idUser);
    public Label findByThing(Thing s);
    public List<Label> findAllByName (String name);
    public Label findLabelByIdLabel(int idLabel);
    public Label findLabelByNameAndIdUser(String nome, int idUser);
}