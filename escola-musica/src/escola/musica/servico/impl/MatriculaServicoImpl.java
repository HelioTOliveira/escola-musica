package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Matricula;
import escola.musica.modelo.MatriculaVO;

@Service(value = "MatriculaServico")
@Transactional
public class MatriculaServicoImpl implements escola.musica.servico.MatriculaServico {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MatriculaVO> listarTodas() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(Matricula.LISTAR_TODAS).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> listarTodasAtivas() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Matricula.ListarTodasAtivas").getResultList();
	}
	
	@Override
	public void salvar(Matricula matricula) {
		// TODO Auto-generated method stub
		entityManager.merge(matricula);
	}

	@Override
	public Matricula obterPorId(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Matricula.class, id);
	}

}