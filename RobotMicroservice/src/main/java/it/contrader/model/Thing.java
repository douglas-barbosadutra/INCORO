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
@Table(name="thing")

public class Thing {
	
	@Id
	@Column(name = "idThing")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idThing;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description", columnDefinition ="LONGTEXT")
	@NotNull
	private String description;
	
	@Column(name = "protocol", columnDefinition ="LONGTEXT")
	@NotNull
	private String protocol;
	
	@Column(name = "code", columnDefinition = "LONGTEXT")
	@NotNull
	private String code;
	
	@Column(name = "image")
	@NotNull
	private String image;
	
	@Column(name = "xml")
	@NotNull
	private String xml;
	
	@Column(name="id_user")
	@NotNull
	private Integer idUser;
	
	@ManyToOne
	@JoinColumn(name = "idLabel")
	private Label label;//ok perche si ragiona già con l'oggetto
	
	@OneToMany(mappedBy="thing")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Hardware> hardware;
	
	@OneToMany(mappedBy = "thing")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Behavior> behavior;
	
	@OneToMany(mappedBy = "thing")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<LinkTK> linktk;
}