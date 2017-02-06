/**
 * 
 */
package com.quantifiedCare.service;

import java.util.List;

import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;

/**
 * @author arvind
 *
 */
public interface IMyOrganizationService extends IGenericService<Organizations>{
	States getStateIdByName(String name);
	List<Organizations> fetchAllOrganization();
	List<Users> getUserByOrgIdRoleId(Long orgId, Long roleId);
	
	
	
}
