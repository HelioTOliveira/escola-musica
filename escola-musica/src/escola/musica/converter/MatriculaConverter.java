package escola.musica.converter;

import javax.faces.convert.FacesConverter;

import escola.musica.modelo.Matricula;

@FacesConverter("matriculaConverter")
public class MatriculaConverter extends EntityConverter<Matricula>{

	public MatriculaConverter(){
		super(Matricula.class);
	}
}
