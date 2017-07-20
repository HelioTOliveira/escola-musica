package escola.musica.modelo;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
//anotação @Embeddable para vicular novas colunas em um tabela existente 
@Embeddable
public class Endereco {

	private String logradouro;
	private Integer numero;
	private String cep;
	private Cidade cidade;

	@NotEmpty(message= "Informe o logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	@NotNull(message= "Informe o númeor!")
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

	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
