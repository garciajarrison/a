package co.com.avaluo.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.avaluo.model.entity.Usuario;

/**
 * 
 * Entity DAO
 * 
 * @author Miquel Millan
 * @version 1.0.0
 * 
 */
@Named
public class EjemploDAO implements IEjemploDAO {
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		trans.commit();
	}

	public void deleteEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		trans.commit();
	}

	public void updateEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		trans.commit();
	}

	public Usuario getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<?> list = session
				.createQuery("from Entity where id=?").setParameter(0, id)
				.list();
		
		trans.commit();
		return (Usuario) list.get(0);
	}

	public List<Usuario> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Usuario> list = (List<Usuario>) session.createQuery("from Entity").list();
		
		trans.commit();
		return list;
	}

}
