package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LinkTKDTO {
	private Integer idLinkTK;
	private ThingDTO thing;
	private KeywordDTO keyword;
}
