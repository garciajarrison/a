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

	public void addLicencia(Licencia entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void deleteLicencia(Licencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateLicencia(Licencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Licencia getLicenciaById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		return (Licencia) session
				.createQuery("from Licencia where id=?").setParameter(0, id)
				.uniqueResult();
	}

	public List<Licencia> getLicencias() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery("from Licencia").list();
	}

}
