package escola.musica.bean;

import javax.annotation.ManagedBean;

import escola.musica.modelo.Curso;

@ManagedBean
public class CursoBean {

	private Curso curso = new Curso();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
