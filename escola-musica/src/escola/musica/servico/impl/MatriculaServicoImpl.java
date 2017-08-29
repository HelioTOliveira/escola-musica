package escola.musica.servico.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Aluno;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.MatriculaVO;
import escola.musica.modelo.ParametrosBuscaMatriculas;
import escola.musica.util.DataUtils;

@Service(value = "MatriculaServico")
@Transactional
public class MatriculaServicoImpl implements escola.musica.servico.MatriculaServico {

	private static final String FORMATO_BANCO_MYSQL = "yyyy-MM-dd";
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MatriculaVO> pesquisar(ParametrosBuscaMatriculas parametros) {
		StringBuilder builder = new StringBuilder("select new escola.musica.modelo.MatriculaVO("
				+ "m.id, m.numero, m.dataMatricula, m.aluno.nome, m.curso.nome) from  Matricula m where m.id is not null ");
		
		if(parametros.getAluno() != null){
			builder.append(" and m.aluno = :aluno");
		}
		
		if(parametros.getCursos() != null && !parametros.getCursos().isEmpty()){
			builder.append(" and m.curso in (:cursos)");
		}
		
		if(parametros.getDataInicial() != null){
			builder.append(" and m.dataMatricula >= '" +  DataUtils.obterDataFormatoBanco
					(parametros.getDataInicial(), FORMATO_BANCO_MYSQL) +"'");
		}
		
		if(parametros.getDataFinal() != null){
			builder.append(" and m.dataMatricula <= '" + DataUtils.obterDataFormatoBanco
					(parametros.getDataFinal(), FORMATO_BANCO_MYSQL) +"'");
		}
		
		Query query = entityManager.createQuery(builder.toString());
		
		if(parametros.getAluno() != null){
			query.setParameter("aluno", parametros.getAluno());
		}		
		
		if(parametros.getCursos() != null && !parametros.getCursos().isEmpty()){
			query.setParameter("cursos", parametros.getCursos());
		}
		
		return query.getResultList();
	}

}
