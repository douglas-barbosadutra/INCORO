package it.contrader.dto;


	public class LabelDTO {
		private int LabelId;
		private String Labelname;


		public LabelDTO(String Labelname) {
			this.Labelname = Labelname;

		}
		
		public LabelDTO() {} 
		
		public int getLabelId() {
			return LabelId;
		}

		public void setLabelId(int LabelId) {
			this.LabelId = LabelId;
		}

		public String getLabelname() {
			return Labelname;
		}

		public void setLabelname(String Labelname) {
			this.Labelname = Labelname;
		}



	}



