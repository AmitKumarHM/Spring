/**
 * 
 */
package com.quantifiedCare.service;

import com.quantifiedCare.core.entity.Statuses;

/**
 * @author kiwitech
 *
 */
public interface IStatusesService extends IGenericService<Statuses>{

	 Statuses getStatusesById(Long id);
}
