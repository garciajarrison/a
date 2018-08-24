package co.com.avaluo.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Licencia;
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
		Usuario usuarioTmp = (Usuario)session.createQuery("from Usuario where correo=:correo and estado = true")
				.setParameter("correo", users.getCorreo())
				.uniqueResult();
		
		if(usuarioTmp != null &&
			Util.getInstance().verificarContrasenna(users.getContrasena(), usuarioTmp.getContrasena())) {
			
			return usuarioTmp;
		}else {
			return null;
		}
	}
	
	public void addUsuario(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteUsuario(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateUsuario(Usuario entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Usuario getUsuarioById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		return (Usuario) session
				.createQuery("from Usuario where id=?").setParameter(0, id)
				.uniqueResult();
	}

	public List<Usuario> getUsuarios() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery("from Usuario").list();
	}
	
	public Usuario consultaIdentificacion(String tipoIdentificacion, String identificacion, int id, int rol_Id) {
		
		Session session = getSessionFactory().getCurrentSession();
		if (rol_Id == 0) {
			return (Usuario) session
					.createQuery("from Usuario u where u.tipoDocumento=? and u.identificacion=? and u.empresa.id = ?")
					.setParameter(0, tipoIdentificacion).setParameter(1, identificacion).setParameter(2, id) 
					.uniqueResult();
			
		}
		else {
			return (Usuario) session
					.createQuery("from Usuario u where u.tipoDocumento=? and u.identificacion=? and u.empresa.id = ? and u.rol.id=?")
					.setParameter(0, tipoIdentificacion).setParameter(1, identificacion).setParameter(2, id).setParameter(3, rol_Id) 
					.uniqueResult();
		}
	}

	public void actualizarUltimaConn(Licencia licencia) {
		Session session = getSessionFactory().getCurrentSession();
		licencia.setFechaUltimaConn(new Date());
		session.update(licencia);
	}

	public void bloquearCuenta(String correo) {
		Session session = getSessionFactory().getCurrentSession();
		session.createQuery("update Usuario set estado = false where correo = :correo")
			.setParameter("correo", correo).executeUpdate();
		
	}

	public void cambiarClave(String correo, String clave) {
		Session session = getSessionFactory().getCurrentSession();
		Usuario usr = consultarUsuarioPorCorreo(correo);
		if(usr != null) {
			usr.setContrasena(clave);
			session.update(usr);
		}
	}

	public Usuario consultarUsuarioPorCorreo(String correo) {
		Session session = getSessionFactory().getCurrentSession();
		return (Usuario) session
				.createQuery("from Usuario u where u.correo = :correo and u.estado = true")
				.setParameter("correo", correo)
				.uniqueResult();
	}

	public List<Usuario> getUsuariosActivos() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery("from Usuario where estado = true").list();
	}

}
