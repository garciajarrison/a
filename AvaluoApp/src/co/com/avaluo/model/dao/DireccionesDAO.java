package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Direcciones;


@Repository
public class DireccionesDAO implements IDireccionesDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addDirecciones(Direcciones entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteDirecciones(Direcciones entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateDirecciones(Direcciones entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Direcciones getDireccionesById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Direcciones) session
				.createQuery("from Direcciones where id=?").setParameter(0, id)
				.uniqueResult();
	}

	


}
