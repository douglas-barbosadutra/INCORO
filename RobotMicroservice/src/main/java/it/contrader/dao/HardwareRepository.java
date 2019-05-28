package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Hardware;
import java.util.List;

public interface HardwareRepository extends CrudRepository <Hardware, Integer> {
	public Hardware findHardwareByIdHardware(int idHardware);
	public List<Hardware> findAllByName(String name);	
}