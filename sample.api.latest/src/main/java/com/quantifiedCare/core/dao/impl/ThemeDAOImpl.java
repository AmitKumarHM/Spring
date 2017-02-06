/**
 * 
 */
package com.quantifiedCare.core.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quantifiedCare.core.dao.AbstractDAO;
import com.quantifiedCare.core.dao.IThemeDAO;
import com.quantifiedCare.core.entity.Theme;
import com.quantifiedCare.core.repository.ThemeRepository;

/**
 * @author user
 *
 */
@Repository
public class ThemeDAOImpl extends AbstractDAO<Theme> implements IThemeDAO{

	/** The ThemeRepository. */
	@Autowired
	public ThemeRepository themeRepository;
	
	@Override
	@PostConstruct
	protected void setBaseJPARepository() {
		super.setBaseRepository(this.themeRepository);
		
	}

}
