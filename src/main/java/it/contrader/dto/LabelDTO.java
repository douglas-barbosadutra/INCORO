package it.contrader.dto;


	public class LabelDTO {
		private int idLabel;
		private int idUsers;
		private String nomeLabel;

		public LabelDTO(String name) {
			this.nomeLabel = name;
		}
		
		public LabelDTO() {}

		public int getIdlabel() {
			return idLabel;
		}

		public void setIdLabel(int idLabel) {
			this.idLabel = idLabel;
		}

		public int getIdusers() {
			return idUsers;
		}

		public void setIdusers(int idusers) {
			this.idUsers = idusers;
		}

		public String getNome() {
			return nomeLabel;
		}

		public void setNome(String nomeLabel) {
			this.nomeLabel = nomeLabel;
		} 
}