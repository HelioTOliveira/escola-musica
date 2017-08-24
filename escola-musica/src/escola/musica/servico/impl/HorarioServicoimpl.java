package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Horario;
import escola.musica.servico.HorarioServico;

@Service("horarioServicoimpl")
@Transactional
public class HorarioServicoimpl implements HorarioServico{

	@PersistenceContext
	private EntityManager entityMnager;
	
	@Override
	public void salvar(Horario horario) {
		entityMnager.merge(horario);
		
	}

	@Override
	public List<Horario> listarPorTurma(Integer idturma) {
		
		return entityMnager.createNamedQuery(Horario.LISTAR_POR_TURMA, Horario.class)
				.setParameter("idTurma", idturma).getResultList();
	}

}
