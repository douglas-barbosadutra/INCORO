package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.ThingDTO;
import it.contrader.dto.HardwareDTO;
import it.contrader.model.Thing;
import it.contrader.model.Hardware;

public class ConverterHardware {

	public static HardwareDTO toDTO (Hardware hardware) {
		HardwareDTO hardwareDTO = null;
		if(hardware != null) {
			hardwareDTO = new HardwareDTO();
			hardwareDTO.setIdHardware(hardware.getIdHardware());
			hardwareDTO.setDescription(hardware.getDescription());
			hardwareDTO.setName(hardware.getName());
			hardwareDTO.setMaster(hardware.isMaster());
			
			ThingDTO thing = new ThingDTO();
			thing = ConverterThing.toDTO(hardware.getThing());
			
			hardwareDTO.setThing(thing);
		}	
		return hardwareDTO; 
	}
	
	public static Hardware toEntity(HardwareDTO hardwareDTO) {
		Hardware hardware = null;
		if (hardwareDTO != null) {
			hardware = new Hardware();
			hardware.setIdHardware(hardwareDTO.getIdHardware());
			hardware.setDescription(hardwareDTO.getDescription());
			hardware.setName(hardwareDTO.getName());
			hardware.setMaster(hardwareDTO.isMaster());
			
			Thing thing = new Thing();
			thing = ConverterThing.toEntity(hardwareDTO.getThing());
			
			hardware.setThing(thing);
		}
		return hardware;
	}
	
	public static List<HardwareDTO> toListDTO(List<Hardware> list) {
		List<HardwareDTO> listWorksDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Hardware works : list) {
				listWorksDTO.add(ConverterHardware.toDTO(works));
			}
		}
		return listWorksDTO;
	}
	
	public static List<Hardware> toListEntity(List<HardwareDTO> listHardwareDTO) {
		List<Hardware> list = new ArrayList<>();
		if (!listHardwareDTO.isEmpty()) {
			for (HardwareDTO hardwareDTO : listHardwareDTO) {
				list.add(ConverterHardware.toEntity(hardwareDTO));
			}
		}
		return list;
	}	
}
