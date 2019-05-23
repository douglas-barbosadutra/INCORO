package it.contrader.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Table(name="actionEvent")


public class ActionEvent {
	
	@Id
	@Column(name = "idActionEvent")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idActionEvent;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description", columnDefinition ="LONGTEXT")
	@NotNull
	private String description;
	
	@Column(name = "type")
	@NotNull
	private int type;
	
	
	@ManyToOne
	@JoinColumn(name = "idLabel")
	private Label label;
	
	


}
