package br.gov.sp.fatec.projetoweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Cliente;





public class ClienteDao {
	
	private EntityManager manager;

	public ClienteDao() {
		manager = PersistenceManager
                .getInstance().getEntityManager();
	}

	public ClienteDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public Cliente searchById(String id) {
		return manager.find(Cliente.class, Long.valueOf(id));
	}
	
	public void save(Cliente cliente) throws RollbackException {
		try {
			manager.getTransaction().begin();
			saveWithoutCommit(cliente);
			manager.flush();
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void saveWithoutCommit(Cliente cliente) {
		if(cliente.getId() == null) {
			manager.persist(cliente);		
		}else {
			manager.merge(cliente);
		}
	}
	
	public Cliente searchByName(String nome) {
		String consulta = "select c from Cliente c where nome = :nome";
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);
		query.setParameter("nome", nome);
		return query.getSingleResult();
	}
	
	
	public void excluir(String id) throws RollbackException{
		Cliente cliente = searchById(id);
		try {
			manager.getTransaction().begin();
			manager.remove(cliente);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

}
