package co.com.avaluo.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.avaluo.model.entity.SoftwareAccount;

/**
 * 
 * Entity DAO
 * 
 * @author Miquel Millan
 * @version 1.0.0
 * 
 */
@Named
public class SoftwareDAO implements ISoftwareDAO {
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(SoftwareAccount entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		trans.commit();
	}

	public void deleteEntity(SoftwareAccount entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		trans.commit();
	}

	public void updateEntity(SoftwareAccount entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		trans.commit();
	}

	public SoftwareAccount getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<?> list = session
				.createQuery("from SoftwareAccount where id=?").setParameter(0, id)
				.list();
		
		trans.commit();
		return (SoftwareAccount) list.get(0);
	}

	public List<SoftwareAccount> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<SoftwareAccount> list = (List<SoftwareAccount>) session.createQuery("from SoftwareAccount").list();
		
		trans.commit();
		return list;
	}

}
