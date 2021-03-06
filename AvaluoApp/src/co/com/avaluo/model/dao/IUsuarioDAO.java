package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;


public interface IUsuarioDAO {
	
	Usuario login(Usuario users);
	
	void addUsuario(Usuario entity);

	void deleteUsuario(Usuario entity);

	void updateUsuario(Usuario entity);

	Usuario getUsuarioById(int id);

	List<Usuario> getUsuarios();
	
	Usuario consultaIdentificacion(String tipoIdentificacion, String identificacion, int id, int rol_Id);

	void actualizarUltimaConn(Licencia licencia);

	void bloquearCuenta(String correo);

	void cambiarClave(String correo, String clave);

	Usuario consultarUsuarioPorCorreo(String correo);

	List<Usuario> getUsuariosActivos();
	
	List<Usuario> getAvaluadores();

}
