package escola.musica.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.dao.GenericDAO;
import escola.musica.modelo.Aluno;
import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;
import escola.musica.servico.AlunoServico;
import escola.musica.servico.CidadeServico;

@Controller("alunoBean")
@Scope("session")
public class AlunoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1025252140353914359L;

	private Aluno aluno;
	private List<Aluno> alunos;
	private List<Estado> estados;
	
	@Autowired
	private AlunoServico alunoServico;
	@Autowired
	private CidadeServico cidadeServico;
	
	public void iniciarBean(){
		alunos = alunoServico.listarTodos();
		estados = Arrays.asList(Estado.values());
	}
	
	public void novoAluno(){
		aluno = new Aluno();
	}
	
	public void salvar(){
		
		alunoServico.salvar(aluno);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Aluno cadastrado com sucesso"));
		
		alunos = alunoServico.listarTodos();
		aluno = null;
	}
	
	public void editar(Aluno aluno){
	
		this.aluno = aluno;
		
	}
	
	public void voltar(){
		
		this.aluno = null;
	}
	
	public String getDataAtual(){
		
		// Intacia o calendar pois � um inbterface
		Calendar calendar = Calendar.getInstance();
		// Pega a data atual menos 6 anos
		calendar.add(Calendar.YEAR, -6);
		// formata a data e retorna
		return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
	}
	
	public List<Cidade> getCidadesDoEstado(){
		
		return cidadeServico.obterCidadesDoEstado(aluno.getEndereco().getCidade().getEstado());
	}
	
	public void enviarFoto(FileUploadEvent event){
		
		try {
			 byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
			 aluno.setFoto(foto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public StreamedContent getImagemAluno(){
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idAluno = mapaParametros.get("id_aluno");
		if(idAluno != null){
			Aluno alunoBanco = new GenericDAO<Aluno>(Aluno.class)
					.obterPorId(new Integer(idAluno));
			return alunoBanco.getImagem();
		}
		
		return new DefaultStreamedContent();
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}	
	
}
