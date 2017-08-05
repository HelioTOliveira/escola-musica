package escola.musica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
	
	@NamedQuery(name = Matricula.LISTAR_TODAS, query= Matricula.LISTAR_TODAS),
	@NamedQuery(name ="Matricula.ListarTodasAtivas", query= "from Matricula where ativo = true")
})

public class Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8083358052410703771L;

	private Integer id;
	private String numero;
	private Date dataMatricula;
	private Aluno aluno;
	private Curso curso;
	private boolean ativo = true;
	private Date dataDesativacao;

	//Atraves do contrutor da propria classe
//	public static final String LISTAR_TODAS = "select new Matricula (id, dataMatricula, numero, aluno.nome, curso.nome)"
//			+ "from Matricula";
	//Ou cria uma classe com o contrutor abaixo
	public static final String LISTAR_TODAS = "select new escola.musica.modelo.MatriculaVO (id, numero, "
			+ "dataMatricula, aluno.nome, curso.nome) from Matricula";
	
	public Matricula(){}
	
	public Matricula(Integer id, Date data, String numero, String nomeAluno, String nomeCurso){
		setId(id);
		setNumero(numero);
		setDataMatricula(data);
		setAluno(new Aluno());
		this.aluno.setNome(nomeAluno);
		setCurso(new Curso(nomeCurso));
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotEmpty(message = "O campo 'N�mero' � obrigat�rio")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@NotNull(message = "O campo 'Data Matr�cula' � obrigat�rio")
	@Temporal(TemporalType.DATE)
	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	@NotNull(message = "Selecione o aluno")
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@NotNull(message = "Selecione um curso")
	@ManyToOne
	@JoinColumn(name = "id_curso")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
