package com.quantifiedCare.core.spring.orm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.quantifiedCare.util.common.QuantifiedCarePropertyUtil;

/**
 * The Class QuantifiedCareLocalContainerEntityManagerFactoryBean.
 */
public class QuantifiedCareLocalContainerEntityManagerFactoryBean extends LocalContainerEntityManagerFactoryBean {

    /**
     * Instantiates a new QuantifiedCare local container entity manager factory bean.
     */
    public QuantifiedCareLocalContainerEntityManagerFactoryBean() {
        String schemaName = QuantifiedCarePropertyUtil.getProperty("schemaName");
        Map<String, String> propertyMap = new HashMap<String, String>(10);
        propertyMap.put("hibernate.default_schema", schemaName);
        super.setJpaPropertyMap(propertyMap);
    }

}
