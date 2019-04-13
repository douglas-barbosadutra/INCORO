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
import org.springframework.lang.Nullable;

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
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idLabel")
	private Label label;
	
	@Column(name = "code", columnDefinition = "LONGTEXT")
	@NotNull
	
	private String code;
	
	@Column(name = "image")
	@NotNull
	private String image;
	
	@Column(name = "xml")
	@NotNull
	private String xml;
}