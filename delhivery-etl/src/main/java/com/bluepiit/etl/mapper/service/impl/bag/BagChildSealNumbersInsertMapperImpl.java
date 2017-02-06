package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import com.bluepiit.etl.mapper.service.bag.BagChildSealNumbersInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DBUtils;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagChildSealNumbersInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements BagChildSealNumbersInsertMapper<K, V, D> {

	
	
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagChildSealNumbersInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.CHILD_BAG_SEAL_NUMBER, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> wayBillMap=new HashMap<K, V>();
	    	 V childWayBillNum=itr.next();
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, dispatchWayBillNum);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_CHILED_BAG_SEAL_NUMBER, childWayBillNum);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
	    	 persistList.add(wayBillMap);
	    }	
		
}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagChildSealNumbersInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.CHILD_BAG_SEAL_NUMBER, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 V wayBillNumber=delhiveryCollection.getValueFromArrayWithPipe(itr);
	    	 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(wayBillNumber.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagChildSealNumbersUpdateMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList,
			HashMap<K, V> redisDispatchMap) {
         V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap,redisDispatchMap);
		
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.IST, insertDispatchMap,redisDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	HashMap<K, V> istMap=(HashMap<K, V>)itr.next();
	    	HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.ID, istMap);
	    	V mongoId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
	    	String istNumber=null;
	    	SqlSession session =null;
			try{
				session = DBUtils.getSessionFactory().openSession(ExecutorType.SIMPLE);
				istNumber=(String)session.selectOne("selectWbnFromIST",mongoId);
			}catch(Exception ex){
			
			}finally {
				session.close();
			}
	    	 HashMap<K, V> bagIstMap=new HashMap<K, V>();
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_IST_NUMBER, (V)istNumber);
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation+DelhiveryEtlConstants.PIPE));
	    	 persistList.add(bagIstMap);
	    }	
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagChildSealNumbersUpdateStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString,
			HashMap<K, V> redisDispatchMap) {
		V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.BAG_SEAL_NUMBER, insertDispatchMap,redisDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.IST, insertDispatchMap,redisDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	
	    	HashMap<K, V> istMap=(HashMap<K, V>)itr.next();
	    	HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.ID, istMap);
	    	V mongoId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
	    	String istNumber=null;
	    	SqlSession session =null;
			try{
				session = DBUtils.getSessionFactory().openSession(ExecutorType.SIMPLE);
				istNumber=(String)session.selectOne("selectWbnFromIST",mongoId);
			}catch(Exception ex){
			
			}finally {
				session.close();
			}
	    	 insertString.append(bagSealNumber.toString());
			 insertString.append(!DelhiveryUtils.isNULL(istNumber)?istNumber.toString():DelhiveryEtlConstants.PIPE);
			 insertString.append(operation+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }
		
	}

}
