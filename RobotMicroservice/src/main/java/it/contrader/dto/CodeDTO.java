package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodeDTO {
		private Integer idCode;
		private String name;
		private String body;
		private String type;
		private BehaviorDTO behavior = new BehaviorDTO();
}
