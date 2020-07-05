package br.gov.sp.fatec.projetoweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import br.gov.sp.fatec.projetoweb.entity.Roupa;





public class RoupaDao {
	private EntityManager manager;

	
	public RoupaDao() {
		 manager = PersistenceManager
	                .getInstance().getEntityManager();
	}

	public RoupaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void save(Roupa roupa) throws RollbackException {
		try {
			manager.getTransaction().begin();
			manager.flush();
			saveWithoutCommit(roupa);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void saveWithoutCommit(Roupa roupa) {
		if(roupa.getId() == null) {
			manager.persist(roupa);		
		}else {
			manager.merge(roupa);
		}
	}
	
	public Roupa searchByID(Long id) {
		return manager.find(Roupa.class, id);
	}
	

	/*public Roupa ifRoupaAlugada() {
		String consulta = "select r from Roupa r"
				+ " inner join r.item";
		try {
			
		}catch(NoResultException nre) {
			return null;
		}
	}*/
	
}
