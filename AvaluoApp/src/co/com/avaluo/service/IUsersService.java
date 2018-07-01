package co.com.avaluo.service;

import co.com.avaluo.model.entity.Usuario;


public interface IUsersService {
	
	Usuario login(Usuario usuario) throws Exception;
		
}
