package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Cotizacion;

@Repository
public class CotizacionDAO implements ICotizacionDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Cotizacion entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Cotizacion entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Cotizacion entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Cotizacion getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Cotizacion where id=?").setParameter(0, id)
				.list();
		
		return (Cotizacion) list.get(0);
	}
	
	
	public Cotizacion getCustomer(String idCustomer) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Customer where customerIdentification=?").setParameter(0, idCustomer)
				.list();
		
		return (Cotizacion) list.get(0);
	}

	public List<Cotizacion> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Cotizacion")
				.list();
		return (List<Cotizacion>) list;
	}	

	
	
	
	

}
