package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.excptions.LoginRepetidoException;
import escola.musica.modelo.Usuario;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.EnvioEmailServico;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.servico.UsuarioServico;
import escola.musica.util.GeradorSenhaAleatoria;

@Service
@Transactional
public class UsuarioProfessorServicoImpl implements UsuarioProfessorServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EnvioEmailServico envioEmailServico;
	
	@Override
	public void salvar(UsuarioProfessor usuarioProfessor) {
		// TODO Auto-generated method stub
		usuarioProfessor.setLogin(usuarioProfessor.getEmail());
		Usuario usuarioSalvo = usuarioServico.obterUsuarioPeloLogin(usuarioProfessor.getLogin());	
		if(usuarioSalvo != null && !usuarioSalvo.getId().equals(usuarioProfessor.getId())){
			throw new LoginRepetidoException("Já existe um usuario cadastrado com este email");
		}
		
		if(usuarioProfessor.getId() == null){
			String senhaGerada = GeradorSenhaAleatoria.gerarSenhaAleatoria(6);
			System.out.print(senhaGerada);
			String senhaCriptografada = passwordEncoder.encodePassword(senhaGerada, null);
			usuarioProfessor.setSenha(senhaCriptografada);
			envioEmailServico.enviarEmailCadastroUsuarioProfessor(usuarioProfessor, senhaGerada);
			//TODO - Enviar email com login e senha
		}
		entityManager.merge(usuarioProfessor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<UsuarioProfessor> listarTodos() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(UsuarioProfessor.LISTAR_TODOS).getResultList();
	}

}
