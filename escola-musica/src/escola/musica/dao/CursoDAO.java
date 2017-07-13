package escola.musica.dao;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Curso> listarTodos(){
		// Obeter o EntityManager
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		return entityManager.createQuery("from Curso").getResultList();		
		
	}

	public void excluir(Curso curso) {
		
		// Obeter o EntityManager
		EntityManager entityManager = JPAUtil.getEntityManager();
		//Iniciar uma transação
		entityManager.getTransaction().begin();
		
		//Vincular o curso com o banco
		curso = entityManager.merge(curso);
		
		//Salvar ou alterar no banco
		entityManager.remove(curso);
		//Confirmar
		entityManager.getTransaction().commit();
		//Fechar o banco
		entityManager.close();
		
	}
}
