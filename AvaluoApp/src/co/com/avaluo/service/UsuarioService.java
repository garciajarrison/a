package co.com.avaluo.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IUsersDAO;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class UsuarioService implements IUsersService {

	@Inject
	IUsersDAO usersDAO;

	@Transactional(readOnly = false)
	public Usuario login(Usuario users) {
		return usersDAO.login(users);
	}

	public IUsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
}
