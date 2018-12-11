package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Propietarios;

@Repository
public class PropietariosDAO implements IPropietariosDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Propietarios entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Propietarios entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Propietarios entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Propietarios getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Propietarios where id=?").setParameter(0, id)
				.list();
		
		return (Propietarios) list.get(0);
	}



	public List<Propietarios > listaPropietarios(int idVisita) {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Propietarios> list = (List<Propietarios>) session.createQuery("from Propietarios where visitas.id = :idVisita order by nombre")
				.setParameter("idVisita", idVisita).list();
		return list;
	}	
}
