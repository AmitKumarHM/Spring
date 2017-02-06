package com.bluepiit.etl.mapper.service.dispatch;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface RouteDispatchInsertMapper<K, V, D> extends RowMapper<K, V, D> {
	public void routeDispatchInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);
	public void routeDispatchInsertMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList);
}
