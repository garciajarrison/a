package co.com.avaluo.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;

@Named
public class EmpresaDAO implements IEmpresaDAO {
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		trans.commit();
	}

	public void deleteEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		trans.commit();
	}

	public void updateEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		trans.commit();
	}

	public Estrato getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<?> list = session
				.createQuery("from MarketCategories where id=?").setParameter(0, id)
				.list();
		
		trans.commit();
		return (Estrato) list.get(0);
	}

	public List<Estrato > getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Estrato> list = (List<Estrato>) session.createQuery("from MarketCategories").list();
		
		trans.commit();
		return list;
	}

	@Override
	public Empresa getEmpresaPorUsuario(Usuario usuario) {
		Session session = getSessionFactory().getCurrentSession();
		return (Empresa) session.createQuery("from Empresa where").uniqueResult();
	}

}
