package escola.musica.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import escola.musica.dao.CursoDAO;
import escola.musica.modelo.Curso;
import escola.musica.modelo.TipoCurso;

@ManagedBean
@SessionScoped
public class CursoBean {

	private Curso curso;
	private List<TipoCurso> tipos = Arrays.asList(TipoCurso.values());
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Curso> cursosAccordion = new ArrayList<Curso>();

	public CursoBean(){
		cursos = new CursoDAO().listarTodos();
		cursosAccordion = CursoDAO.listarCursosAccordion();
		curso = new Curso();
	}
	
	public List<Curso> getCursosAccordion() {
		return cursosAccordion;
	}

	public void setCursosAccordion(List<Curso> cursosAccordion) {
		this.cursosAccordion = cursosAccordion;
	}

	public String salvar(){
		
		new CursoDAO().salvar(curso);
		cursos = new CursoDAO().listarTodos();
		curso = new Curso();
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Curso Salvo com Sucesso"));
		return "curso_lista?faces-redirect=true";
	}
	
	public String editar(Curso curso){
		
		this.curso = curso;
		
		return "curso_formulario?faces-redirect=true";
	}
	
	public void prepararExclusao(Curso curso){
		
		this.curso = curso;
	}
	
	public void excluir(){
		
		new CursoDAO().excluir(curso);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Curso excluído com Sucesso"));
		cursos = new CursoDAO().listarTodos();
	}
	
	public String getDataAtual(){
		
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<TipoCurso> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoCurso> tipos) {
		this.tipos = tipos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
