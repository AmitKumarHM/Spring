/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IPatientDAO;
import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.repository.PatientRepository;

/**
 * @author kiwitech
 *
 */
@Repository
public class PatientDAOImpl extends AbstractDAO<Organizations> implements IPatientDAO {
	
	@Autowired
	PatientRepository addPatientRepository;

	
	@Override
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.addPatientRepository);
		
	}


	public List<Users> getAllCareGiverByOrgID(Long id, Long roleid) {
		List<Users> usersList = addPatientRepository.getAllCareGiverByOrgID(id, roleid);
		return usersList;
	}


	public Organizations getOrganizationById(Long org_id) {
		Organizations organizations = addPatientRepository.getOrganizationById(org_id);
		return organizations;
	}


	public List<Users> getAllPatientListByOrgIDAndRoleId(Long role_id) {
		List<Users> usersList = addPatientRepository.getAllPatientListByOrgIDAndRoleId(role_id);
		return usersList;
	}


	public void deletePatientBySelectedId(Long id) {
		//addPatientRepository.deletePatientBySelectedId(id);
		
	}

	public Users retriveUsersByUserIdAndRoleId(Long user_id, Long role_id) {
		Users users = addPatientRepository.retriveUsersByUserIdAndRoleId(user_id, role_id);
		return users;
	}
}
