package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Label;

import java.util.List;

public interface LabelDAO extends CrudRepository<Label, Integer> {

	public List<Label> findAllByUser(User u);
    public Label findByThings(Thing s);
}
