package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Matricula;

@Service("matriculaServico")
@Transactional
public class MatriculaServico implements escola.musica.servico.MatriculaServico {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> listarTodas() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Matricula").getResultList();
	}

	@Override
	public void salvar(Matricula matricula) {
		// TODO Auto-generated method stub
		entityManager.merge(matricula);
	}

}
