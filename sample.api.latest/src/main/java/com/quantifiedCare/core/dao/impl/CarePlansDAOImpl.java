/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.ICarePlansDAO;
import com.quantifiedCare.core.entity.CarePlans;
import com.quantifiedCare.core.repository.CarePlansRepository;

/**
 * @author kiwitech
 *
 */
@Repository
public class CarePlansDAOImpl extends AbstractDAO<CarePlans> implements ICarePlansDAO{
	
	/** The user carePlansRepository. */
	@Autowired
	public CarePlansRepository carePlansRepository;
	
	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.carePlansRepository);
		
	}

}
