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
	private Integer id;
	private String code;
	private String image;
	private String name;
	private String xml;
	private Integer idUser;
	private Integer idLabel;
}