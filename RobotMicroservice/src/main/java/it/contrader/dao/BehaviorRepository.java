package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Behavior;

import java.util.List;

public interface BehaviorRepository extends CrudRepository <Behavior, Integer> {
	public Behavior findBehaviorByIdBehavior(int idBehavior);
	public List<Behavior> findAllByName(String name);
}