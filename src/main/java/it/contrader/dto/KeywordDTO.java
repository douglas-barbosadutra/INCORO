package it.contrader.dto;

import java.util.List;

import it.contrader.model.Thing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDTO {
	private Integer idKeyword;
	private String name;
	private List<ThingDTO> thing;

}
