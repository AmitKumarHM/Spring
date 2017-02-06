package com.bluepiit.etl.diskstorage;

public interface DiskOperation {
	public Object get(String key);
	public void set(String key, Object value);
	public void expiry(String key,int expiryTime);
	public  boolean has(String key, Object value);
	public  void destroyPool();
}
