package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.SoftwareAccount;


public interface ISoftwareDAO {
	public void addEntity(SoftwareAccount entity);

	public void updateEntity(SoftwareAccount entity);
	
	public void deleteEntity(SoftwareAccount entity);
	
	public SoftwareAccount getEntity(int id);

	public List<SoftwareAccount> getEntities();
}
