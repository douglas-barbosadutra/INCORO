package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="linktk")
public class LinkTK {
	
	@Id
	@Column(name = "idLinkTK")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLinkTK;
	
	@ManyToOne
	@JoinColumn(name="idThing")
	private Thing thing;
	
	@ManyToOne
	@JoinColumn(name="idKeyword")
	private Keyword keyword;
}
