package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDTO {
	private Integer idHardware;
	private String name;
	private String description;
	private ThingDTO thing = new ThingDTO();
}
