package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hardware")

public class Hardware {
	
	@Id
	@Column(name ="idHardware")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHardware;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	@Column(name ="description", columnDefinition = "LONGTEXT" )
	private String description;
	
	@Column(name="master") 
	@NotNull
	private boolean master;
	
	@ManyToOne
	@JoinColumn(name="idThing")
	private Thing thing;
}
