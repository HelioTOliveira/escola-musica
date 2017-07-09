package escola.musica.modelo;

public enum TipoCurso {

	CORDAS("Cordas"),
	MADERAS("Madeiras"),
	METAIS("Metais"),
	PERCUSSAO("Percussâo");
	
	private String label;
	
	private  TipoCurso(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
