package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Empresa;

@Repository
public class EmpresaDAO implements IEmpresaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Empresa getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Empresa where id=?").setParameter(0, id)
				.list();
		
		return (Empresa) list.get(0);
	}

	public List<Empresa > getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Empresa> list = (List<Empresa>) session.createQuery("from Empresa").list();
		
		return list;
	}

	

}
