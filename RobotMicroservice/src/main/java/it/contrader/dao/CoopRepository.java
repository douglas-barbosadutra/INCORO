package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Coop;

public interface CoopRepository extends CrudRepository<Coop, Integer> {
}