package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Coop;
import it.contrader.model.Label;
import it.contrader.model.Thing;
import it.contrader.model.User;

import java.util.List;

public interface CoopRepository extends CrudRepository<Coop, Integer> {


}