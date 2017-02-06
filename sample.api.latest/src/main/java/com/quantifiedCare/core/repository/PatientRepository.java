/**
 * 
 */
package com.quantifiedCare.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quantifiedCare.core.entity.Organizations;
import com.quantifiedCare.core.entity.Users;
import com.quantifiedCare.core.jpa.GenericJPARepository;

/**
 * @author kiwitech
 *
 */
public interface PatientRepository extends GenericJPARepository<Organizations>{
	
	/**
	 * @param id
	 * @return
	 */
	@Query("SELECT organizations FROM Organizations organizations where organizations.id  = :id")
	Organizations getOrganizationById(@Param("id") Long id);
	
	/*@Query("SELECT roles FROM Organizations organizations where organizations.id  = :id")
	Organizations getRolesById(@Param("id") Long id);*/
	
	/**
	 * @param id
	 * @return
	 */
	@Query("SELECT users FROM Users users where users.organization.id  = :id AND users.role.id= :roleid")
	List<Users> getAllCareGiverByOrgID(@Param("id") Long id, @Param("roleid") Long roleid);
	
	
	
	/**
	 * @param user_id
	 * @param org_id
	 * @param role_id
	 * @return
	 */
	@Query("SELECT users FROM Users users where users.role.id  = :role_id order by user_id desc")
	List<Users> getAllPatientListByOrgIDAndRoleId(@Param("role_id") Long role_id);
	
	/**
	 * @param id
	 */
	@Modifying
	@Query("update  Users users set users.status = :status where users.id = :id")
	void deletePatientBySelectedId(@Param("id") Long id,@Param("status") String status);
	
	/*@Modifying
	@Query("update Job job set job.status = :status where job.id = :jobId")
	void updateJobStatus(@Param("jobId") Long jobId, @Param("status") String status);
	*/
	
	/**
	 * @param user_id
	 * @param roleid
	 * @return
	 */
	@Query("SELECT users FROM Users users where users.id  = :user_id AND users.role.id= :roleid")
	Users getCareGiverByUsersIdAndRoleId(@Param("user_id") Long user_id, @Param("roleid") Long roleid);
	
	/**
	 * @param org_id
	 * @param role_id
	 * @return
	 */
	@Query("SELECT users FROM Users users where users.organization.id = :org_id AND users.role.id  = :role_id")
	Users retriveUsersByOrganizationId(@Param("org_id") Long org_id,@Param("role_id") Long role_id);
	
	
	/**
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	@Query("SELECT users FROM Users users where users.id = :user_id AND users.role.id  = :role_id")
	Users retriveUsersByUserIdAndRoleId(@Param("user_id") Long user_id, @Param("role_id") Long role_id);
}






