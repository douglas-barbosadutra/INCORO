package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coop {
	
	@Id
	@Column(name = "idCoop")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCoop;
	
	@Column(name = "tOne")
	@NotNull
	private String tOne;

	@Column(name = "tTwo")
	@NotNull
	private String tTwo;
	
}