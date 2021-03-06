package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Avaluos;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.DetalleCotizacion;

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
	
	public void addAvaluo(Avaluos entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Cotizacion entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateAvaluo(Avaluos entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
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
	
	public  List<Avaluos> getAvaluos() {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("select a from Avaluos a, Propiedad p, DetalleCotizacion d, Cotizacion c where a.propiedad.id=p.id and p.id=d.propiedad.id and c.id=d.cotizacion.id ")
				.list();
		
		return (List<Avaluos>) list;
	}
	
	public Cotizacion getCustomer(String idCustomer) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Customer where customerIdentification=?").setParameter(0, idCustomer)
				.list();
		
		return (Cotizacion) list.get(0);
	}

	public List<Cotizacion> getEntities(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Cotizacion where empresa_id=?").setParameter(0, id)
				.list();
		return (List<Cotizacion>) list;
	}	

	
	public List<DetalleCotizacion> getDetCotizacion(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from DetalleCotizacion where cotizacion_id=?").setParameter(0, id)
				.list();
		return (List<DetalleCotizacion>) list;
	}	
	
	
	

}
