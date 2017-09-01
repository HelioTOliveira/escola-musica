package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Aluno;
import escola.musica.servico.AlunoServico;

@Service("alunoServico")
@Transactional
public class AlunoServicoImpl implements AlunoServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void salvar(Aluno aluno) {
		// TODO Auto-generated method stub
		entityManager.merge(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listarTodos() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Aluno").getResultList();
	}

	@Override
	public Aluno obterPorEmail(String email) {
		
		List<Aluno> alunos = entityManager.createNamedQuery(Aluno.OBETER_POR_EMAIL, Aluno.class).setParameter("email",
				email).getResultList();
		
		if(!alunos.isEmpty()){
			return alunos.get(0);
		}else{
			return null;
		}
	}

}
