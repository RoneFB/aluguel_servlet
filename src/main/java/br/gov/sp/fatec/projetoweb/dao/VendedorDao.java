package br.gov.sp.fatec.projetoweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Vendedor;




public class VendedorDao {
	
	private EntityManager manager;

	public VendedorDao() {
		 manager = PersistenceManager
	                .getInstance().getEntityManager();
	}

	public VendedorDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public Vendedor searchById(Long id) {
		return manager.find(Vendedor.class, id);
	}
	
	public void save(Vendedor vendedor) throws RollbackException {
		try {
			manager.getTransaction().begin();
			manager.flush();
			saveWithoutCommit(vendedor);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void saveWithoutCommit(Vendedor vendedor) {
		if(vendedor.getId() == null) {
			manager.persist(vendedor);		
		}else {
			manager.merge(vendedor);
		}
	}
	
	public Vendedor searchByName(String nome) {
		String consulta = "select v from Vendedor v where nome = :nome";
		TypedQuery<Vendedor> query = manager.createQuery(consulta, Vendedor.class);
		query.setParameter("nome", nome);
		return query.getSingleResult();
	}
	
	public void excluir(Long id) throws RollbackException{
		Vendedor vendedor = searchById(id);
		try {
			manager.getTransaction().begin();
			manager.remove(vendedor);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
}
