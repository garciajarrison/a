package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Tablas;

@Repository
public class TablasDAO implements ITablasDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Tablas entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Tablas entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Tablas entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Tablas getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Tablas where id=?").setParameter(0, id)
				.list();
		
		return (Tablas) list.get(0);
	}

	public List<Tablas> getEntities( ) {
		Session session = getSessionFactory().getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Tablas> list = (List<Tablas>) session.createQuery("from Tablas where estado = true").list();
		
		return list;
	}

}