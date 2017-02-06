package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.RouteDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class RouteDispatchInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements RouteDispatchInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void routeDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
				 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.ROUTE, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    int indexCount=1;
		while(itr.hasNext()){
	    	 HashMap<K, V> routeMap=(HashMap<K, V>)itr.next();
	    	 ArrayList<V> locationArray=delhiveryCollection.getList((K)DelhiveryEtlKeys.LOCATION, insertDispatchMap);
	    	 
	    	 HashMap<K, V> dateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DATE, routeMap);
	    	 V routeDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, dateMap);
	    	 V speed=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.SPEED, routeMap);
	    	 V accurancy=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ACCURANCY, routeMap);
	    	 V wayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.WAYBILLNUM, routeMap);
	    	
	    	 insertString.append(!DelhiveryUtils.isNULL(locationArray.get(DelhiveryEtlConstants.START_INDEX))?DelhiveryUtils.isNULL(locationArray.get(DelhiveryEtlConstants.START_INDEX))+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
	    	 insertString.append(!DelhiveryUtils.isNULL(locationArray.get(DelhiveryEtlConstants.FIRST_INDEX))?DelhiveryUtils.isNULL(locationArray.get(DelhiveryEtlConstants.FIRST_INDEX))+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
	    	 insertString.append(speed.toString());
	    	 insertString.append(wayBillNum.toString());
	    	 insertString.append(accurancy.toString());
	    	 insertString.append((V)(!DelhiveryUtils.isNULL(routeDate)?routeDate.toString().substring(DelhiveryEtlConstants.FIRST_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE));
	    	 insertString.append(routeDate.toString());
	    	 insertString.append(dispatchWayBillNum.toString());
	    	 insertString.append(new Integer(indexCount).toString());
	    	 insertString.append(operation);
	    	 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    	 indexCount++;
	    	 
	    }
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void routeDispatchInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
				 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.ROUTE, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    int indexCount=1;
		while(itr.hasNext()){
	    	 HashMap<K, V> pickUpRequestMap=new HashMap<K, V>();
	    	 HashMap<K, V> routeMap=(HashMap<K, V>)itr.next();
	    	 ArrayList<V> locationArray=delhiveryCollection.getList((K)DelhiveryEtlKeys.LOCATION, insertDispatchMap);
	    	 
	    	 HashMap<K, V> dateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DATE, routeMap);
	    	 V routeDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, dateMap);
	    	 V speed=delhiveryCollection.getValue((K)DelhiveryEtlKeys.SPEED, routeMap);
	    	 V accurancy=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ACCURANCY, routeMap);
	    	 V wayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.WAYBILLNUM, routeMap);
	    	
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_LOCATION_LONGITUTE,locationArray.get(DelhiveryEtlConstants.START_INDEX));
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_LOCATION_LONGITUTE,locationArray.get(DelhiveryEtlConstants.FIRST_INDEX));
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_SPEED, speed);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_WAYBILLNUM, wayBillNum);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_ACCURANCY, accurancy);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_ROUTE_DATE, routeDate);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_ROUTE_DATETIME, routeDate);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_INDEX_COUNT, (V)new Integer(indexCount));
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	    	 indexCount++;
	    	 persistList.add(pickUpRequestMap);
	    }
		
	}
	
	
}
