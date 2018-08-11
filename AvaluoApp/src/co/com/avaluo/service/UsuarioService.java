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
	public void addUsuario(Usuario entity) {
		getEntityDAO().addUsuario(entity);
	}

	@Transactional(readOnly = false)
	public void deleteUsuario(Usuario entity) {
		getEntityDAO().deleteUsuario(entity);
	}

	@Transactional(readOnly = false)
	public void updateUsuario(Usuario entity) {
		getEntityDAO().updateUsuario(entity);
	}

	public Usuario getUsuarioById(int id) {
		return getEntityDAO().getUsuarioById(id);
	}

	public List<Usuario> getUsuarios() {	
		return getEntityDAO().getUsuarios();
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
