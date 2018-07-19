package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.model.entity.Usuario;

@Repository
public class UsuarioDAO implements IUsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Usuario login(Usuario users) {
		Session session = getSessionFactory().getCurrentSession();
		return (Usuario)session.createQuery("from Usuario where correo=? and contrasena = ?")
				.setParameter(0, users.getCorreo())
				.setParameter(1, users.getContrasena())
				.uniqueResult();
	}
	
	public void addEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateEntity(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Usuario getEntityById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Usuario where id=?").setParameter(0, id)
				.list();
		
		return (Usuario) list.get(0);
	}

	public List<Usuario> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Usuario> list = (List<Usuario>) session.createQuery("from Usuario").list();
		
		return list;
	}

	public Usuario consultaIdentificacion(String identificacion, int id, int rol_Id) {
		Session session = getSessionFactory().getCurrentSession();
		
		List<?> list = session
				.createQuery("from Usuario u where u.identificacion=? and u.empresa.id = ? and u.rol.id=?").setParameter(0, identificacion).setParameter(1, id).setParameter(2, rol_Id) 
				.list();
		Usuario usuario = new Usuario();
		if (list.size() > 0) {
			usuario = (Usuario) list.get(0);
		}
		
		return usuario;
	}


}
