package it.contrader.dto;


	public class LabelDTO {
		private int idLabel;
		private int idusers;
		private String nomeLabel;


		public LabelDTO(String Labelname) {
			this.nomeLabel = Labelname;

		}
		
		public LabelDTO() {}

		public int getIdLabel() {
			return idLabel;
		}

		public void setIdLabel(int idLabel) {
			this.idLabel = idLabel;
		}

		public int getIdusers() {
			return idusers;
		}

		public void setIdusers(int idusers) {
			this.idusers = idusers;
		}

		public String getNomeLabel() {
			return nomeLabel;
		}

		public void setNomeLabel(String nomeLabel) {
			this.nomeLabel = nomeLabel;
		} 
		
		



	}



