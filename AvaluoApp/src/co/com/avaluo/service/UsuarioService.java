package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;

@Service
@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usersDAO;
	
	public IUsuarioDAO getEntityDAO() {
		return usersDAO;
	}

	public void setEntityDAO(IUsuarioDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	@Transactional(readOnly = false)
	public Usuario login(Usuario users) {
		return usersDAO.login(users);
	}
	
	@Transactional(readOnly = false)
	public void addEntity(Usuario entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Usuario entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Usuario entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Usuario getEntityById(int id) {
		return getEntityDAO().getEntityById(id);
	}

	public List<Usuario> getEntities() {	
		return getEntityDAO().getEntities();
	}

	public Usuario  consultaIdentificacion(String tipoIdentificacion, String identificacion, int id, int rol_Id) {
		return getEntityDAO().consultaIdentificacion(tipoIdentificacion, identificacion, id, rol_Id);
	}

	@Transactional(readOnly = false)
	public void bloquearCuenta(String correo) {
		getEntityDAO().bloquearCuenta(correo);
	}
	
	@Transactional(readOnly = false)
	public void actualizarUltimaConn(Licencia licencia) {
		getEntityDAO().actualizarUltimaConn(licencia);
	}
	
	@Transactional(readOnly = false)
	public void cambiarClave(String correo, String clave) {
		getEntityDAO().cambiarClave(correo, clave);
	}
	
	public Usuario consultarUsuarioPorCorreo(String correo) {
		return getEntityDAO().consultarUsuarioPorCorreo(correo);
	}

}
