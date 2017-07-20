package escola.musica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;

public class CidadeDAO {

	public static List<Cidade> obetrCidadesDoEstado(Estado estado){
		
		EntityManager em = JPAUtil.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Cidade> cidades = em.createQuery("from Cidade where estado = :uf")
				.setParameter("uf", estado).getResultList();
		
		em.close();
		
		return cidades;
	}
}
