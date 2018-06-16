package co.com.avaluo.service;

import co.com.avaluo.model.entity.Users;


public interface IUsersService {
	
	Users login(Users users) throws Exception;
		
}
