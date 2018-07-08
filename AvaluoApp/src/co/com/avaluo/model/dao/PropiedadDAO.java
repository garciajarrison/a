package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.TipoPropiedad;

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

	public void addPropiedad(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deletePropiedad(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updatePropiedad(Propiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Propiedad getPropiedadById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Propiedad) session
				.createQuery("from Propiedad where id=?").setParameter(0, id)
				.uniqueResult();
	}

	public List<Propiedad> getPropiedades( String propertyType) {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Propiedad> list = (List<Propiedad>) session.createQuery("from Propiedad where zone = ?").setParameter(0, propertyType).list();
		return list;
	}

	public List<TipoPropiedad> getListaTipoPropiedad(int idEmpresa) {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TipoPropiedad> list = (List<TipoPropiedad>) session.createQuery("from TipoPropiedad where empresa.id = :idEmpresa")
				.setParameter("idEmpresa", idEmpresa).list();
		return list;
	}

	public void addTipoPropiedad(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void updateTipoPropiedad(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public void deleteTipoPropiedad(TipoPropiedad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public TipoPropiedad getTipoPropiedad(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (TipoPropiedad)session
				.createQuery("from TipoPropiedad where id=?").setParameter(0, id)
				.uniqueResult();
	}

}
