package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ThingDTO;
import it.contrader.dto.BehaviorDTO;
import it.contrader.model.Behavior;
import it.contrader.model.Thing;

public class ConverterBehavior {
	public static BehaviorDTO toDTO(Behavior behavior) {
		BehaviorDTO BehaviorDTO = null;
		if(behavior != null) {
			BehaviorDTO  = new BehaviorDTO();
			BehaviorDTO.setIdBehavior(behavior.getIdBehavior());
			BehaviorDTO.setName(behavior.getName());	
			ThingDTO thing = new ThingDTO();
			thing = ConverterThing.toDTO(behavior.getThing());
			BehaviorDTO.setThing(thing);
		}
		return BehaviorDTO;		
	}
	
	public static Behavior toEntity(BehaviorDTO behaviorDTO) {
		Behavior behavior = null;
		if(behaviorDTO!= null) {
			behavior = new Behavior();
			behavior.setIdBehavior(behaviorDTO.getIdBehavior());
			behavior.setName(behaviorDTO.getName());
			
			Thing thing = new Thing();
			thing = ConverterThing.toEntity(behaviorDTO.getThing());
			behavior.setThing(thing);
		}
		return behavior;
	}
	
	public static List<BehaviorDTO> toListDTO(List<Behavior> list) {
		List<BehaviorDTO> listBehaviorDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Behavior behavior : list) {
				listBehaviorDTO.add(ConverterBehavior.toDTO(behavior));
			}
		}
		return listBehaviorDTO;
	}
	
	public static List<Behavior> toListEntity(List<BehaviorDTO> listBehaviorDTO) {
		List<Behavior> list = new ArrayList<>();
		if (!listBehaviorDTO.isEmpty()) {
			for (BehaviorDTO behaviorDTO : listBehaviorDTO) {
				list.add(ConverterBehavior.toEntity(behaviorDTO));
			}
		}
		return list;
	}
}