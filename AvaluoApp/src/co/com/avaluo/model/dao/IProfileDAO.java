package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.ProfileAccount;


public interface IProfileDAO {
	public void addEntity(ProfileAccount entity);

	public void updateEntity(ProfileAccount entity);
	
	public void deleteEntity(ProfileAccount entity);
	
	public ProfileAccount getEntity(int id);

	public List<ProfileAccount> getEntities();
}
