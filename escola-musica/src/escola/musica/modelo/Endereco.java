package escola.musica.modelo;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
//anota��o @Embeddable para vicular a outra classe 
@Embeddable
public class Endereco {

	private String logradouro;
	private Integer numero;
	private String cep;
	private Cidade cidade = new Cidade();

	@NotEmpty(message= "Informe o logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	@NotNull(message= "Informe o n�mero!")
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@NotEmpty(message= "Informe o cep")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	//@ManyToone: para relacionar a tabela no banco n para 1
	//@JoinColumn(name = "id_cidade"): Colocar o nome na coluna da tabela
	//ender�o para pode indentificar no banco
	@ManyToOne	
	@JoinColumn(name = "id_cidade")
	@NotNull(message="Selecione a cidade")
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
