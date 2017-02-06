/**
 * 
 */
package com.quantifiedCare.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quantifiedCare.core.entity.Statuses;
import com.quantifiedCare.core.jpa.GenericJPARepository;

/**
 * @author arvind
 *
 */
public interface StatusesRepository  extends GenericJPARepository<Statuses>{
	
	@Query("SELECT statuses from Statuses statuses WHERE statuses.id =:id")
	Statuses getStatusesById(@Param("id") Long id );
	
}
