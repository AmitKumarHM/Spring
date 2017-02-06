/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IOrganizationDAO;
import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.repository.OrganizationRepository;


/**
 * @author arvind
 *
 */
@Repository
public class OrganizationDAOImpl extends AbstractDAO<Organizations> implements IOrganizationDAO {

	
	/** The user repository. */
	@Autowired
	public OrganizationRepository organizationRepository;
	
	
	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.organizationRepository);
		
	}

	
	public States getStateIdByName(Long state) {
		States stateObj  = organizationRepository.getStateIdByName(state);
		return stateObj;
	}

	
	public List<Organizations> fetchAllOrganization() {
		List<Organizations> organizations = organizationRepository.fetchAllOrganization();
		return organizations;
	}
	
	
	public Organizations getOrganization(Long id) {
		Organizations organizations = organizationRepository.getOrganization(id);
		return organizations;
	}
	
	
	public void deleteOrganization(Long id) {
		organizationRepository.deleteOrganization(id);
	}

	
	public List<Users> getUserByOrgIdRoleId(Long orgId, Long roleId) {
		List<Users> users = organizationRepository.getUserByOrgIdRoleId(orgId, roleId);
		return users;
	}

	
	public Organizations getLastInsertedOrganizationObject() {
		Organizations organizations = organizationRepository.getLastInsertedOrganizationObject();
		return organizations;
	}
}
