package br.gov.sp.fatec.projetoweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Aluguel;
import br.gov.sp.fatec.projetoweb.entity.Roupa;




public class AluguelDao {
	
	private EntityManager manager;
	RoupaDao roupaDao;
	ClienteDao clienteDao;
	VendedorDao vendedorDao;


	public AluguelDao() {
		 this(PersistenceManager.getInstance().getEntityManager());
	}
	
	public AluguelDao(EntityManager entityManager) {
		this.manager = entityManager;
		roupaDao = new RoupaDao();
		clienteDao = new ClienteDao();
		vendedorDao = new VendedorDao();
	}

	public void save(Aluguel aluguel) throws RollbackException {
		try {
			manager.getTransaction().begin();
			saveWithoutCommit(aluguel);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void saveWithoutCommit(Aluguel aluguel) {
		for(Roupa roupa: aluguel.getRoupas()){
			if(roupa.getId() == null) {
				roupaDao.saveWithoutCommit(roupa);
			}
		}
		if(aluguel.getCliente() != null && aluguel.getCliente().getId() == null) {
			clienteDao.save(aluguel.getCliente());
		}if(aluguel.getVendedor() != null && aluguel.getVendedor().getId() == null) {
			vendedorDao.saveWithoutCommit(aluguel.getVendedor());
		}
		
		if(aluguel.getId() == null) {
			manager.persist(aluguel);		
		}else {
			manager.merge(aluguel);
		}
	}
	
	public Aluguel aluguelCompletebyID(String idCli, String idVen) {
		String consulta = "select a from Aluguel a"
				+ " inner join a.vendedor v on v.id = a.vendedor"
				+ " inner join a.cliente c on c.id = a.cliente"
				+ " where c.id = :cliID and v.id = :venID";
		try {
			TypedQuery<Aluguel> query = manager.createQuery(consulta, Aluguel.class);
			query.setParameter("cliID", Long.valueOf(idCli));
			query.setParameter("venID", Long.valueOf(idVen));
			return query.getSingleResult();
			
		}catch(NoResultException nre) {
			return null;
		}
	}
	
	public List<Aluguel> filterAluguelByRoupa(String cor, String tamanho){
		String consulta = "select a from Aluguel a"
				+ " inner join a.roupas r on r.aluguel_roupas = a.id"
				+ " where r.cor = :cor and r.tamanho = :tamanho";
		TypedQuery<Aluguel> query = manager.createQuery(consulta, Aluguel.class);
		query.setParameter("cor", cor);
		query.setParameter("tamanho", tamanho);
		return query.getResultList();
	}
}
