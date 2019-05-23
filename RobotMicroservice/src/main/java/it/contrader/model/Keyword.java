package it.contrader.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy = "keyword")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<LinkTK> linktk;

}



/*
@ManyToMany(cascade = { CascadeType.ALL })
   @JoinTable(
       name = "keyword_thing", 
       joinColumns = { @JoinColumn(name = "idKeyword") }, 
       inverseJoinColumns = { @JoinColumn(name = "idThing") }
   )
   private List<Thing> thing;
*/

/*
@OneToMany
@JoinTable(name = "keyword_thing")
private List<Thing> thing;
*/
