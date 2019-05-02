package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ThingDTO {
	private Integer idThing;
	private String code;
	private String image;
	private String name;
	private String xml;
	
	private UserDTO user = new UserDTO();
	private LabelDTO label = new LabelDTO();
}