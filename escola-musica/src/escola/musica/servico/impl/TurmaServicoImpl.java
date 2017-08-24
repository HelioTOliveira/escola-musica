package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Turma;
import escola.musica.servico.TurmaServico;

@Service("turmaServicoImpl")
@Transactional
public class TurmaServicoImpl implements TurmaServico {

	@PersistenceContext
	private EntityManager entityMnager;
	
	@Override
	public void salvar(Turma turma) {
		entityMnager.merge(turma);

	}

	@Override
	public List<Turma> listarTodas() {
		return entityMnager.createQuery("from Turma", Turma.class).getResultList();
	}

}
