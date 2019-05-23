package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name="users")
	public class User {
		
		@Id
		@Column(name = "idUser")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer idUser;
		
		
		@Column(name = "username")
		@NotNull
		private String username;
		
		@NotNull
		@Column(name = "password")
		private String password;
		

		@NotNull
		@Column(name = "type")
		private Integer type;

	

	}

	


