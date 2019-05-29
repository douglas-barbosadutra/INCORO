package it.contrader.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Label {
	
	@Id
	@Column(name = "idLabel")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLabel;
	
	@Column(name = "name")
	@NotNull
	private String name;

	@OneToMany(mappedBy="label")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Thing> thing;
	
	@Column(name="idUser")
	@NotNull
	private Integer idUser;
	
	@OneToMany(mappedBy="label")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<ActionEvent> actionEvent;
	
}