package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.ProfileAccount;


public interface IProfileService {
	
	public void addEntity(ProfileAccount entity);
	
	public void updateEntity(ProfileAccount entity);

	public void deleteEntity(ProfileAccount entity);
	
	public ProfileAccount getEntityById(int id);
	
	public List<ProfileAccount> getEntitys();
}
