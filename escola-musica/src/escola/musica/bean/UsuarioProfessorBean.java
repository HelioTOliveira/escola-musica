package escola.musica.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.stereotype.Controller;

import escola.musica.excptions.LoginRepetidoException;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.util.Mensagem;

@Controller
@Scope("session")
public class UsuarioProfessorBean implements Serializable {

	private static final long serialVersionUID = -1446484411632089350L;

	private UsuarioProfessor usuarioProfessor;
	private List<UsuarioProfessor> usuarioProfessores;

	@Autowired
	private UsuarioProfessorServico professorServico;

	public void iniciarBean() {
		atualizarListaUsuarios();
	}

	public void novoUsuarioProfessor() {
		usuarioProfessor = new UsuarioProfessor();
	}

	public void salvar() {
		try {
			professorServico.salvar(usuarioProfessor);
			atualizarListaUsuarios();
			usuarioProfessor = null;
			Mensagem.mensagemInformacao("Usuário salvo com sucesso");
		} catch (LoginRepetidoException e) {

			Mensagem.mensagemErro(e.getMessage());
		}
	}

	public void editar(UsuarioProfessor usuarioProfessor) {
		this.usuarioProfessor = usuarioProfessor;
	}

	public void cancelar() {
		this.usuarioProfessor = null;
	}

	public void atualizarListaUsuarios() {
		usuarioProfessores = professorServico.listarTodos();
	}

	public UsuarioProfessor getUsuarioProfessor() {
		return usuarioProfessor;
	}

	public void setUsuarioProfessor(UsuarioProfessor usuarioProfessor) {
		this.usuarioProfessor = usuarioProfessor;
	}

	public List<UsuarioProfessor> getUsuarioProfessores() {
		return usuarioProfessores;
	}

	public void setUsuarioProfessores(List<UsuarioProfessor> usuarioProfessores) {
		this.usuarioProfessores = usuarioProfessores;
	}

}
