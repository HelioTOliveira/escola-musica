package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Arquivo;
import escola.musica.modelo.Avaliacao;
import escola.musica.servico.ArquivoServico;
import escola.musica.servico.AvaliacaoServico;

@Transactional
@Service("avaliacaoServico")
public class AvaliacaoServicoImpl implements AvaliacaoServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ArquivoServico arquivoServico;
	
	@Override
	public List<Avaliacao> listarTodos() {
		
		return entityManager.createNamedQuery(Avaliacao.LISTAR_TODOS, Avaliacao.class).getResultList();
	}

	@Override
	public void salvar(Avaliacao avaliacao) {
		Arquivo arquivoSalvo = arquivoServico.inserirArquivoNoSistema
				(avaliacao.getArquivo(), criarDiretorioArquivo(avaliacao));
		avaliacao.setArquivo(arquivoSalvo);
		entityManager.merge(avaliacao);
		
	}

	private String criarDiretorioArquivo(Avaliacao avaliacao) {
		
		return "avaliacoes/"+ avaliacao.getAno() +"/"+ avaliacao.getBimestre().getLabel();
	}

	
}
