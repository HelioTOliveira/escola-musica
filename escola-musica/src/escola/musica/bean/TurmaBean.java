package escola.musica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Curso;
import escola.musica.modelo.DiaSemana;
import escola.musica.modelo.Horario;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.Semestre;
import escola.musica.modelo.Turma;
import escola.musica.servico.CursoServico;
import escola.musica.servico.HorarioServico;
import escola.musica.servico.MatriculaServico;
import escola.musica.servico.TurmaServico;
import escola.musica.util.Mensagem;

@Controller("turmaBean")
@Scope("view")
public class TurmaBean implements Serializable {

	private static final long serialVersionUID = 374615860770008608L;

	private Turma turma;
	private List<Turma> turmas;
	private List<Semestre> semestres;
	private List<Curso> cursos;
	private List<Matricula> matriculas = new ArrayList<Matricula>();
	private Horario horario = new Horario();
	private int indiceHorario;
	
	@Autowired
	private TurmaServico turmaServico;
	@Autowired
	private CursoServico cursoServico;
	@Autowired
	private MatriculaServico matriculaServico;
	@Autowired
	private HorarioServico horarioServico;
	
	public void iniciarBean(){
		semestres =  Arrays.asList(Semestre.values());
		atualizarTurmas();
		matriculas =  matriculaServico.listarTodasAtivas();
		cursos = cursoServico.listarTodos();
	}
	
	public void novarTurma(){
		turma = new Turma();
	}
	
	public void salvarTurma(){
		turmaServico.salvar(turma);
		turma = null;
		atualizarTurmas();
		Mensagem.mensagemInformacao("Turma salva com sucesso!");
	}
	
	public void editarTurma(Turma turma){
		this.turma = turma;
		this.turma.setMatriculas(new ArrayList<Matricula>(this.turma.getMatriculas()));
	}
	
	public void cancelarTurma(){
		this.turma = null;
	}
	
	private void atualizarTurmas(){
		turmas = turmaServico.listarTodas();
	}
	
	public String getLabelMatriculas(){
		StringBuilder label = new StringBuilder("");
		if(turma.getMatriculas().isEmpty()){
			return label.toString();
		}
		
		for(Matricula matricula: turma.getMatriculas()){
			label.append(matricula.getAluno().getNome() + " - " + matricula.getCurso().getNome() 
					+ "<br/>");
		}
		return label.toString();
	}
	
	//Hor�rios
	public void novoHorario(){
		this.horario = new Horario();
	}
	
	public void limparHorario(){
		this.horario = new Horario();
	}
	
	public List<DiaSemana> getDiasSemana(){
		return Arrays.asList(DiaSemana.values());
	}
	
	public void salvarHorario(){
		horario = horarioServico.salvar(horario);
		if(!turma.getHorarios().contains(horario)){
			//turma.getHorarios().remove(horario);
			turma.getHorarios().add(horario);
		}		
		horario = new Horario();
		Mensagem.mensagemInformacao("Hor�rio adicionado com sucesso");
	}
	
	public void editarHorario(Horario horario){
		this.horario = horario;
	}
	
	public void excluirHorario(){
		turma.getHorarios().remove(horario);
		horario = new Horario();
		Mensagem.mensagemInformacao("Hor�rio removido com sucesso");
	}
	
	//Hor�rio nova implementa��o
	public void incluirHorario(){
		turma.getHorarios().add(new Horario());
	}
	
	public void prepararRemocaoHorario(int indiceHorario){
		this.indiceHorario = indiceHorario;
	}
	
	public void removerHorario(){
		turma.getHorarios().remove(indiceHorario);
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	
}
