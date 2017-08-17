package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.Audited;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.excptions.LoginRepetidoException;
import escola.musica.modelo.Usuario;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.servico.UsuarioServico;

@Service
@Transactional
public class UsuarioProfessorServicoImpl implements UsuarioProfessorServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Audited
	private UsuarioServico usuarioServico;
	
	@Override
	public void salvar(UsuarioProfessor usuarioProfessor) {
		// TODO Auto-generated method stub
		usuarioProfessor.setLogin(usuarioProfessor.getEmail());
		Usuario usuarioSalvo = usuarioServico.obterUsuarioPeloLogin(usuarioProfessor.getLogin());
		if(usuarioSalvo != null && !usuarioSalvo.getId().equals(usuarioProfessor.getId())){
			throw new LoginRepetidoException("Já existe um usuario cadastrado com este email");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<UsuarioProfessor> listarTodos() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(UsuarioProfessor.LISTAR_TODOS).getResultList();
	}

}
