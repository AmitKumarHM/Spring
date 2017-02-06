package com.bluepiit.etl.diskstorage;

import com.bluepiit.etl.diskstorage.config.RedisConfig;
import com.bluepiit.etl.utils.DelhiveryUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class RedisOperation implements DiskOperation {

	private JedisPool pool;
	private static RedisOperation redisOperation;
	private Jedis jedis;
	
	public RedisOperation() {
		init();
	}

	public static RedisOperation getInstance(){
		return !DelhiveryUtils.isNULL(redisOperation)?redisOperation:new RedisOperation(); 
	}
	
	void init(){
		RedisConfig redisConfig=new RedisConfig();
		pool=new JedisPool(redisConfig.getJedisPoolConfig(),redisConfig.getURL(),redisConfig.getPort());
		jedis=pool.getResource();
	}
	

	@Override
	public Object get(String key) {
		return jedis.get(key);
	}

	@Override
	public void set(String key, Object value) {
		jedis.set(key, value.toString());
	}

	@Override
	public void expiry(String key,int expiryTime) {
		jedis.expire(key, expiryTime);
	}

	@Override
	public boolean has(String key, Object value) {
		String val=jedis.get(key);
		return !(DelhiveryUtils.isNULL(val))?val.equals(value):false;
	}

	@Override
	public void destroyPool() {
		pool.destroy();
	}
}
