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

	public void addEmpresa(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEmpresa(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEmpresa(Empresa entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Empresa getEmpresaById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Empresa) session
				.createQuery("from Empresa where id=?").setParameter(0, id)
				.uniqueResult();
	}

	public List<Empresa> getEmpresas() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery("from Empresa").list();
	}

	

}
