package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

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
	
	@Column(name = "fktouser")
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUser")

	private User user;
	
}