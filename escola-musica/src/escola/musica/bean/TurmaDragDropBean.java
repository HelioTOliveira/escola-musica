package escola.musica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Matricula;
import escola.musica.servico.MatriculaServico;
import escola.musica.util.Mensagem;

@Controller("turmaDragDropBean")
@Scope("session")
public class TurmaDragDropBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 374615860770008608L;

	private List<Matricula> matriculas;
	private List<Matricula> matriculasInseridas = new ArrayList<Matricula>();
	private List<Matricula> matriculasSelecionadas;
	
	@Autowired
	private MatriculaServico matriculaServico;

	public void iniciarBean() {
		matriculas = matriculaServico.listarTodasAtivas();
	}

	public void onMatriculaDrop(DragDropEvent event){
		Matricula matricula = (Matricula) event.getData();
		matriculas.remove(matricula);
		matriculasInseridas.add(matricula);
	}
	
	public void removerMatriculas(){
		if(matriculasSelecionadas.isEmpty()){
			Mensagem.mensagemErro("Selecione ao menos uma matrícula para remover");
			return;
		}
		matriculasInseridas.removeAll(matriculasSelecionadas);
		matriculas.addAll(matriculasSelecionadas);
		Mensagem.mensagemInformacao("Matriculas removidas com sucesso");
	}
	
	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Matricula> getMatriculasInseridas() {
		return matriculasInseridas;
	}

	public void setMatriculasInseridas(List<Matricula> matriculasInseridas) {
		this.matriculasInseridas = matriculasInseridas;
	}

	public List<Matricula> getMatriculasSelecionadas() {
		return matriculasSelecionadas;
	}

	public void setMatriculasSelecionadas(List<Matricula> matriculasSelecionadas) {
		this.matriculasSelecionadas = matriculasSelecionadas;
	}

	public MatriculaServico getMatriculaServico() {
		return matriculaServico;
	}

	public void setMatriculaServico(MatriculaServico matriculaServico) {
		this.matriculaServico = matriculaServico;
	}
	
}
