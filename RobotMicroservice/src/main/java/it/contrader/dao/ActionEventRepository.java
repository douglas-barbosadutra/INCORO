package it.contrader.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.ActionEvent;
import it.contrader.model.Label;

public interface ActionEventRepository extends CrudRepository<ActionEvent, Integer> {
	public ActionEvent findActionEventByIdActionEvent(int idActionEvent);
	public List<ActionEvent> findAllByLabel(Label label);
	

}
