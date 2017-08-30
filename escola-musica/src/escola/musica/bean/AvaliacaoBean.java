package escola.musica.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Avaliacao;

@Controller("avaliacaoBean")
@Scope("view")
public class AvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 6689842322966855745L;

	private Avaliacao avaliacao;

	public void iniciarBean(){
		
	}
	
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
