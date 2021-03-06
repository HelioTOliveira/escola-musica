package escola.musica.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Curso;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.TipoCurso;
import escola.musica.servico.CursoServico;
import escola.musica.servico.MatriculaServico;
import escola.musica.util.Mensagem;

@Controller("cursoTabViewBean")
@Scope("view")
public class CursoTabViewBean implements Serializable{

	private static final long serialVersionUID = -862660658464075437L;
	
	
	private Curso curso;
	private List<TipoCurso> tipos = Arrays.asList(TipoCurso.values());
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Curso> cursosAccordion = new ArrayList<Curso>();
	private Curso cursoExclusao;
	private List<Curso> cursosFiltrados;
	private List<Matricula> matriculas = new ArrayList<Matricula>();
	
	//Com anota��o o proprio spring faz a intancia do objeto
	@Autowired
	private CursoServico cursoServico;
	@Autowired
	private MatriculaServico matriculaSerrvico;
	
//	public CursoBean(){
//		cursos = new CursoDAO().listarTodos();
//		cursosAccordion = CursoDAO.listarCursosAccordion();
//		curso = new Curso();
//	}
	
	public void iniciarBean(){
		
		cursos = cursoServico.listarTodos();
		cursosAccordion = cursoServico.listarCursosAccordin();	
		curso = new Curso();
	}
	
	public void novoCurso(){
		
		curso = new Curso();
	}
	
	public List<Curso> getCursosAccordion() {
		return cursosAccordion;
	}

	public void setCursosAccordion(List<Curso> cursosAccordion) {
		this.cursosAccordion = cursosAccordion;
	}

	public void salvar()throws InterruptedException{ 
		
		Thread.sleep(2000);
		cursoServico.salvar(curso);
		//new CursoDAO().salvar(curso);
		cursos = cursoServico.listarTodos();
		//cursos = new CursoDAO().listarTodos();
		curso = new Curso();
		
		Mensagem.mensagemInformacao("Curso salvo com sucesso!");		
	}
	
	public void editar(Curso curso){
		
		this.curso = curso;
		//matriculas = matriculaSerrvico.listarPorCurso(curso.getId());
 	}
	
	public void prepararExclusao(Curso curso){
		
		this.cursoExclusao = curso;
	}
	
	public void excluir(){
		
		cursoServico.excluir(cursoExclusao);
		Mensagem.mensagemInformacao("Curso exclu�do com Sucesso");
		cursos = cursoServico.listarTodos();
		cursosFiltrados = null;
	}
	
	public void onTabChange(TabChangeEvent event){
		String tituloAba = event.getTab().getTitle();
		if(Objects.equals(tituloAba, "Matriculas")){									  
			  matriculas = matriculaSerrvico.listarPorCurso(curso.getId());
		}else{
			matriculas = null;
		}
	}
	
	public void voltar(){
		curso = null;
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

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}
	
	public Curso getCursoExclusao() {
		return cursoExclusao;
	}

	public void setCursoExclusao(Curso cursoExclusao) {
		this.cursoExclusao = cursoExclusao;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	
}
