package escola.musica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Curso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3223617028339662203L;
	
	private Integer id;	
	private String nome;
	private String descricao;
	private double duracao = 1;
	private TipoCurso tipo;
	private Date dataCriacao;

	@NotNull(message="A data de criação deve ser informada")
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message="O campo nome deve ser informado")
	@Length(min=4, max=50, message="O campo nome deve ter entre 4 e 50 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message="Você deve informa a descrição")	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	@NotNull(message=" Você dave selecionar o tipo do curso!")
	public TipoCurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}
	
	public String obterImagem(){
		
		return nome.toLowerCase().replaceAll("ã", "a").replaceAll("é", "e")
				.replaceAll(" ","_").concat(".png");
	}

}
