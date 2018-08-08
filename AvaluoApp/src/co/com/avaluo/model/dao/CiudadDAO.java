package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Ciudad;

@Repository
public class CiudadDAO implements ICiudadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Ciudad getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Ciudad where id=? order by nombre").setParameter(0, id)
				.list();
		
		return (Ciudad) list.get(0);
	}

	public List<Ciudad > getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Ciudad> list = (List<Ciudad>) session.createQuery("from Ciudad ")
				.list();
		return list;
	}

}
