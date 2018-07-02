package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Propiedad;

@Repository
public class PropiedadDAO implements IPropiedadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Propiedad getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Propiedad where id=?").setParameter(0, id)
				.list();
		
		return (Propiedad) list.get(0);
	}

	public List<Propiedad> getEntities( String propertyType) {
		Session session = getSessionFactory().getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Propiedad> list = (List<Propiedad>) session.createQuery("from Propiedad where zone = ?").setParameter(0, propertyType).list();
		
		return list;
	}

}
