package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Curso;
import escola.musica.servico.CursoServico;

@Service(value = "CursoServico")
@Transactional
public class CursoServicoImpl implements CursoServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void salvar(Curso curso) {
		// TODO Auto-generated method stub
		entityManager.merge(curso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarTodos() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Curso").getResultList();
	}

	@Override
	public void excluir(Curso curso) {
		// TODO Auto-generated method stub
		curso = entityManager.merge(curso);
		entityManager.remove(curso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursosAccordin() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Curso where nome in ('Violino',"
				+ "'Bateria', 'Clarinete','Flauta', 'Guitarra', 'Violão', 'Oboé' ) order by nome").getResultList();
	}

}
