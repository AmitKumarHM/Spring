package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.mapper.service.bag.BagIstInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DBUtils;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagIstInsertMapperImpl<K, V, D>  extends BaseMapperImpl<K, V, D> implements BagIstInsertMapper<K, V, D> {

	private static final Logger LOG = LoggerFactory.getLogger(BagIstInsertMapperImpl.class);
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagIstInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.IST, insertDispatchMap);
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
				LOG.error("Exception occur in insert DispatchRoute "+ex.getMessage());
			}finally {
				session.close();
			}
	    	 HashMap<K, V> bagIstMap=new HashMap<K, V>();
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_IST_NUMBER, (V)istNumber);
	    	 bagIstMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
	    	 persistList.add(bagIstMap);
	    }		

	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagIstInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		
		V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.BAG_SEAL_NUMBER, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryBagKeyAndColumn.IST, insertDispatchMap);
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
				LOG.error("Exception occur in insert DispatchRoute "+ex.getMessage());
			}finally {
				session.close();
			}
	    	 insertString.append(bagSealNumber.toString());
			 insertString.append((!DelhiveryUtils.isNULL(istNumber))?istNumber.toString()+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }
	}

}
