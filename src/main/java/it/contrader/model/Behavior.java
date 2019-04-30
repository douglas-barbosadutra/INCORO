package it.contrader.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="behavior")

public class Behavior {
	
	@Id
	@Column(name ="idBehavior")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBehavior;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "idThing")
	private Thing thing;
	
	@OneToMany(mappedBy="behavior")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Code> code;
}
