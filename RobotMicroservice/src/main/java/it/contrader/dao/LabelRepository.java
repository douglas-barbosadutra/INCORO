package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Label;
import it.contrader.model.Thing;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface LabelRepository extends CrudRepository<Label, Integer> {

	public List<Label> findAllByIdUser(int idUser);
	public Label findLabelByIdUser(int idUser);
    public Label findByThing(Thing s);
    public List<Label> findAllByName (String name);
    public Label findLabelByIdLabel(int idLabel);
    public Label findLabelByNameAndIdUser(String nome, int idUser);
    
    @Query("SELECT e FROM Label e WHERE e.idUser = ?1 AND e.name LIKE ?2%")
    public List<Label> findByIdUserAndNameContainingIgnoreCase(int idUser, String name);
    
    //@Query("SELECT e FROM Label e WHERE e.name LIKE ?1%")
    //public List<Label> findByNameContainingIgnoreCase(String name);
}