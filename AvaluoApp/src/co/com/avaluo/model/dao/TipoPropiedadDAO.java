package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.TipoPropiedad;

@Repository
public class TipoPropiedadDAO implements ITipoPropiedadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public TipoPropiedad getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from TipoPropiedad where id=?").setParameter(0, id)
				.list();
		
		return (TipoPropiedad) list.get(0);
	}

	public List<TipoPropiedad> getEntities( String propertyType) {
		Session session = getSessionFactory().getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<TipoPropiedad> list = (List<TipoPropiedad>) session.createQuery("from TipoPropiedad where tipoVivienda = ?").setParameter(0, propertyType).list();
		
		return list;
	}

}
