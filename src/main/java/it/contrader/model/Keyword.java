package it.contrader.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="keyword")

public class Keyword {
	@Id
	@Column(name="idKeyword")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKeyword;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
/*
	@ManyToMany(cascade = { CascadeType.ALL })
	   @JoinTable(
	       name = "keyword_thing", 
	       joinColumns = { @JoinColumn(name = "idKeyword") }, 
	       inverseJoinColumns = { @JoinColumn(name = "idThing") }
	   )
	   private List<Thing> thing;
	*/

	@OneToMany
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinTable(
		       name = "keyword_thing"
		       
		       
		       )
	private List<Thing> thing;
	
		
	
	

}
