package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.SoftwareAccount;


public interface ISoftwareService {
	
	public void addEntity(SoftwareAccount entity);
	
	public void updateEntity(SoftwareAccount entity);

	public void deleteEntity(SoftwareAccount entity);
	
	public SoftwareAccount getEntityById(int id);
	
	public List<SoftwareAccount> getEntitys();
}
