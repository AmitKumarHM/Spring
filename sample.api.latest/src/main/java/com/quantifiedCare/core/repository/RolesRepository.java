/**
 * 
 */
package com.quantifiedCare.core.repository;

import com.quantifiedCare.core.entity.Roles;
import com.quantifiedCare.core.jpa.GenericJPARepository;

/**
 * @author kiwitech
 *
 */
public interface RolesRepository extends GenericJPARepository<Roles>{
	
		
	/*@Query("SELECT roles FROM Roles AS roles")
	Users getRolesName();*/

}
