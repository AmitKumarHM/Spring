package com.bluepiit.etl.diskstorage.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;

import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig implements Config {

	private int port;
	private String uRL;
	private JedisPoolConfig jedisPoolConfig;
	private Properties prop;
    private InputStream input;
    private static final Logger LOG = LoggerFactory.getLogger(RedisConfig.class);
	
	public RedisConfig() {
		init();
	}

	private void init() {
		prop = new Properties();
	    input = getClass().getClassLoader().getResourceAsStream(DelhiveryEtlConstants.REDIS_CONFIG_FILE);
	    try {
			prop.load(input);
			port=Integer.parseInt(((String)prop.get(DelhiveryEtlConstants.PORT)).trim());
			uRL=(String)prop.get(DelhiveryEtlConstants.URL);
			jedisPoolConfig=new JedisPoolConfig();
			LOG.info("Port number and url are successfully loaded from property file");
	    } catch (IOException e) {
	    	LOG.error("Port number and url are unable to load from property file "+e.toString());
	    }
	}

	public JedisPoolConfig getJedisPoolConfig() {
		return jedisPoolConfig;
	}
	
	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getURL() {
		return uRL;
	}

}
