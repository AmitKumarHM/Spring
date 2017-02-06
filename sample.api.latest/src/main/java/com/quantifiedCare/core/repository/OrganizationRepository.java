/**
 * 
 */
package com.quantifiedCare.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.States;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.jpa.GenericJPARepository;

/**
 * @author arvind
 *
 */
public interface OrganizationRepository extends GenericJPARepository<Organizations> {
	
	@Query("select states from States states where states.id  = :state")
	States getStateIdByName(@Param("state") Long state );
	
	@Query("SELECT organizations FROM Organizations organizations ORDER BY organizations.id DESC")
	List<Organizations> fetchAllOrganization();
	
	/*@Query("SELECT organizations FROM Organizations organizations ORDER BY organizations CAST(name AS CHAR(100)) DESC")
	List<Organizations> fetchAllOrderedOrganizations();*/
	
	@Query("select users from Users users where users.organization.id= :orgId and users.role.id= :roleId")
	List<Users> getUserByOrgIdRoleId(@Param("orgId") Long orgId, @Param("roleId") Long roleId);
	
	@Query("SELECT organizations FROM Organizations organizations where organizations.id = :id")
	Organizations getOrganization(@Param("id") Long id);
	
	@Query("DELETE FROM Organizations organizations where organizations.id = :id")
	void deleteOrganization(@Param("id") Long id);
	
	/*@Query("DELETE FROM Users users where organizations.id = :id")
	void deleteUsers(@Param("id") Long id);*/
	
	@Query("SELECT MAX(organizations.id) FROM Organizations organizations")
	Organizations getLastInsertedOrganizationObject();
	
}
