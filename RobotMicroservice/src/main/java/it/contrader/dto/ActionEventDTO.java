package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActionEventDTO {
	
	private Integer idActionEvent;
	private String description;
	private String name;
	private LabelDTO label;
	private Integer type;

}