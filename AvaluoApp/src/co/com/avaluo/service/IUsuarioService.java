package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;


public interface IUsuarioService {
	
	void addUsuario(Usuario entity);
	
	void updateUsuario(Usuario entity);

	void deleteUsuario(Usuario entity);
	
	Usuario getUsuarioById(int id);
	
	List<Usuario> getUsuarios();

	Usuario login(Usuario usuario);

	Usuario consultaIdentificacion(String tipoIdentificacion, String identificacion, int id, int rol_Id);

	void bloquearCuenta(String correo);

	void actualizarUltimaConn(Licencia licencia);

	void cambiarClave(String correo, String clave);

	Usuario consultarUsuarioPorCorreo(String correo);

	List<Usuario> getUsuariosActivos();
	
	List<Usuario> getAvaluadores();

	
}
