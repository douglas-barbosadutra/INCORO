package it.contrader.dto;


	public class LabelDTO {
		private int idLabel;
		private int idUsers;
		private String nomeLabel;


		public LabelDTO(String LabelName) {
			this.nomeLabel = LabelName;

		}
		
		public LabelDTO() {}

		public int getIdLabel() {
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

		public String getNomeLabel() {
			return nomeLabel;
		}

		public void setNomeLabel(String nomeLabel) {
			this.nomeLabel = nomeLabel;
		} 
}