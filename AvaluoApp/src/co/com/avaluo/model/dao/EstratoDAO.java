package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Estrato;

@Repository
public class EstratoDAO implements IEstratoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Estrato entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Estrato getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Estrato where id=?").setParameter(0, id)
				.list();
		
		return (Estrato) list.get(0);
	}

	public List<Estrato > getEntities(int idEmpresa) {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Estrato> list = (List<Estrato>) session.createQuery("from Estrato where empresa.id = :idEmpresa")
				.setParameter("idEmpresa", idEmpresa).list();
		return list;
	}

}
