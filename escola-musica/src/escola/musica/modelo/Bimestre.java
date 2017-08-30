package escola.musica.modelo;

public enum Bimestre {

	PRIMEIRO("1º Bimestre"),
	SEGUNDO("2º Bimestre"),
	TERCEIRO("3º Bimestre"),
	QUARTO("4º Bimestre");
	
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
