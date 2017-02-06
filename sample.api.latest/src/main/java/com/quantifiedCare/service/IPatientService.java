/**
 * 
 */
package com.quantifiedCare.service;

import java.util.List;

import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.Users;

/**
 * @author kiwitech
 *
 */
public interface IPatientService extends IGenericService<Organizations>{
	
	/**
	 * @param org_id
	 * @return
	 */
	Organizations getOrganizationById(Long org_id);
	
	/**
	 * @param id
	 * @return
	 */
	List<Users> getAllCareGiverByOrgID(Long id, Long roleid);
	
	/**
	 * @param user_id
	 * @param org_id
	 * @param role_id
	 * @return
	 */
	List<Users> getAllPatientListByOrgIDAndRoleId(Long role_id);
	
	
	/**
	 * @param id
	 */
	void deletePatientBySelectedId(Long id);
	
	Users retriveUsersByUserIdAndRoleId(Long user_id, Long role_id);
}
