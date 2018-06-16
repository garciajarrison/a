package co.com.avaluo.model.dao;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.com.avaluo.model.entity.Users;

@Named
public class UsersDAO implements IUsersDAO {
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Users login(Users users) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Users)session.createQuery("from Users where email=? and password = ?")
				.setParameter(0, users.getEmail())
				.setParameter(1, users.getPassword())
				.uniqueResult();
	}

}
