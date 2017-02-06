package com.quantifiedCare.util.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * The Class PropertyUtil has been role to load the all *.properties file in the buffer and get the properties file
 * attribute from buffer this class provide a static method that name is getProperty.
 */
/**
 * @author Arvind 

 *
 */
public class QuantifiedCarePropertyUtil extends PropertyPlaceholderConfigurer {

    /** The properties map. */
    private static Map<String, String> propertiesMap;

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
        throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            propertiesMap.put(keyStr, resolvePlaceholder(keyStr, props));
        }
    }

    /**
     * Gets the property.
     * 
     * @param name
     *            the name
     * @return the property
     */
    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }
}
