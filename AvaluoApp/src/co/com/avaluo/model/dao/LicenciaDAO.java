package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Licencia;

@Repository
public class LicenciaDAO implements ILicenciaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Licencia entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void deleteEntity(Licencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Licencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Licencia getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Licencia where id=?").setParameter(0, id)
				.list();
		
		return (Licencia) list.get(0);
	}

	public List<Licencia> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		List<Licencia> list = (List<Licencia>) session.createQuery("from Licencia").list();
		
		return list;
	}

}
