package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Departamento;

@Repository
public class DepartamentoDAO implements IDepartamentoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Departamento entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Departamento entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Departamento entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Departamento getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Departamento where id=?").setParameter(0, id)
				.list();
		
		return (Departamento) list.get(0);
	}

	public List<Departamento > getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Departamento> list = (List<Departamento>) session.createQuery("from Departamento ")
				.list();
		return list;
	}

}
