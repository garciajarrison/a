package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.Pais;

@Repository
public class CiudadDAO implements ICiudadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Ciudad entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Ciudad getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		return (Ciudad)  session
				.createQuery("from Ciudad where id=? order by nombre").setParameter(0, id)
				.uniqueResult();
	}

	public List<Ciudad> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Ciudad>) session.createQuery("from Ciudad ")
				.list();
	}

	public List<Pais> getPaises() {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Pais>) session.createQuery("from Pais ")
				.list();
	}

	public List<Departamento> getDepartamentos(int idPais) {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Departamento>) session.createQuery("from Departamento where pais.id = :paisId")
				.setParameter("paisId", idPais)
				.list();
	}

	public List<Ciudad> getCiudades(int idDepartamento) {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Ciudad>) session.createQuery("from Ciudad where departamento.id = :departamentoId")
				.setParameter("departamentoId", idDepartamento)
				.list();
	}
	
	
	

}
