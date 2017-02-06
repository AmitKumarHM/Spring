/**
 * 
 */
package com.quantifiedCare.core.dao;

import com.quantifiedCare.core.entity.Statuses;

/**
 * @author arvind
 *
 */
public interface IStatusesDAO extends IGenericDAO<Statuses>{
	
	 Statuses getStatusesById(Long id);
}
