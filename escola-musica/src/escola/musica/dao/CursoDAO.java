package escola.musica.dao;

import javax.persistence.EntityManager;

import escola.musica.modelo.Curso;

public class CursoDAO {

	public void salvar(Curso curso){
		
		// Obeter o EntityManager
		EntityManager entityManager = JPAUtil.getEntityManager();
		//Iniciar uma transação
		entityManager.getTransaction().begin();
		//Salvar ou alterar no banco
		entityManager.merge(curso);
		//Confirmar
		entityManager.getTransaction().commit();
		//Fechar o banco
		entityManager.close();
	}
}
