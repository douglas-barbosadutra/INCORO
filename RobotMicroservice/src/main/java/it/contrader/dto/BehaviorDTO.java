package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BehaviorDTO {
	private Integer idBehavior;
	private String name;
	private ThingDTO thing = new ThingDTO();
}