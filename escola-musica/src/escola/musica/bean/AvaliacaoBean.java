package escola.musica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Avaliacao;
import escola.musica.modelo.Matricula;
import escola.musica.servico.AvaliacaoServico;
import escola.musica.servico.MatriculaServico;

@Controller("avaliacaoBean")
@Scope("view")
public class AvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 6689842322966855745L;

	private Avaliacao avaliacao;
	private List<Avaliacao> avaliacoes;
	private List<Matricula> matriculas;

	@Autowired
	private AvaliacaoServico avaliacaoServico;
	@Autowired
	private MatriculaServico matriculaServico;

	public void iniciarBean() {
		avaliacoes = avaliacaoServico.listarTodos();
		matriculas = matriculaServico.listarTodasAtivas();
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

}
