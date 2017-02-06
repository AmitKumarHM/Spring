/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IStatusesDAO;
import com.quantifiedCare.core.entity.Statuses;
import com.quantifiedCare.core.repository.StatusesRepository;

/**
 * @author arvind
 *
 */
@Repository
public class StatusesDAOImpl extends AbstractDAO<Statuses> implements IStatusesDAO {
	
	
	/** The user repository. */
	@Autowired
	public StatusesRepository statusesRepository;


	public Statuses getStatusesById(Long id) {
		Statuses status = statusesRepository.getStatusesById(id); 
		return status;
	}

	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.statusesRepository);
	}
}
