package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Hardware;
import it.contrader.model.LinkTK;
import it.contrader.model.Keyword;
import it.contrader.model.Thing;
import it.contrader.dto.KeywordDTO;
import it.contrader.dto.ThingDTO;

import java.util.List;


public interface LinkTKRepository extends CrudRepository <LinkTK, Integer> {

		public List<LinkTK> findAllByKeyword(Keyword key);
		public List<LinkTK> findAllByThing(Thing thing);
		public LinkTK findLinkTKByThingAndKeyword(Thing thing, Keyword keyword);
}