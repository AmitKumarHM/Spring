/**
 * 
 */
package com.quantifiedCare.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantifiedCare.core.dao.ICarePlansDAO;
import com.quantifiedCare.core.entity.CarePlans;
import com.quantifiedCare.service.AbstractService;
import com.quantifiedCare.service.ICarePlansService;

/**
 * @author kiwitech
 *
 */
@Service
public class CarePlansServiceImpl extends AbstractService<CarePlans>implements ICarePlansService{
	
	@Autowired
	ICarePlansDAO carePlansDAO;
	
	@Override
	@PostConstruct
	protected void setGenericDAO() {
		super.setBaseDAO(carePlansDAO);
		
	}

}
