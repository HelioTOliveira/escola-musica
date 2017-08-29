package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Aluno;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.MatriculaVO;
import escola.musica.modelo.ParametrosBuscaMatriculas;

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

	@Override
	public List<Matricula> listarPorCurso(Integer id) {
		return entityManager.createNamedQuery(Matricula.LISTAR_TODAS_POR_CURSO, Matricula.class).setParameter("idCurso", id)
				.getResultList();
		
	}

	@Override
	public void pesquisar(ParametrosBuscaMatriculas parametros) {
		// TODO Auto-generated method stub
		
	}

}
