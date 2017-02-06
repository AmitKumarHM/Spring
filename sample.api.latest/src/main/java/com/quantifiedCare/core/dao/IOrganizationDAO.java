/**
 * 
 */
package com.quantifiedCare.core.dao;

import java.util.List;

import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;

/**
 * @author arvind
 *
 */
public interface IOrganizationDAO extends IGenericDAO<Organizations>{
	
	States getStateIdByName(Long name);
	List<Organizations> fetchAllOrganization();
	List<Users> getUserByOrgIdRoleId(Long orgId, Long roleId);
	Organizations getOrganization(Long id);
	
	/*void deleteUsers( Long id);*/
	
	void deleteOrganization(Long id);
	
	Organizations getLastInsertedOrganizationObject();
}
