package escola.musica.modelo;

public enum Bimestre {

	PRIMEIRO("1� Bimestre"),
	SEGUNDO("2� Bimestre"),
	TERCEIRO("3� Bimestre"),
	QUARTO("4� Bimestre");
	
	private String label;

	private Bimestre(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
