/**
 * 
 */
package com.quantifiedCare.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantifiedCare.core.dao.IThemeDAO;
import com.quantifiedCare.core.entity.Theme;
import com.quantifiedCare.service.AbstractService;
import com.quantifiedCare.service.IThemeService;

/**
 * @author user
 *
 */
@Service
public class ThemeServiceImpl extends AbstractService<Theme>implements IThemeService{
	
	@Autowired
	public IThemeDAO themeDAO;

	
	@Override
	@PostConstruct
	protected void setGenericDAO() {
		super.setBaseDAO(themeDAO);
		
	}

}
