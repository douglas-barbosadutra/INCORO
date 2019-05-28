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
@Table(name="code")
public class Code {
	
	@Id
	@Column(name = "idCode")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCode;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "body")
	@NotNull
	private String body;
	
	@Column(name = "type")
	@NotNull
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "idBehavior")
	private Behavior behavior;
}
