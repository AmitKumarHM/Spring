package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.bag.BagInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagInsertMapperImpl<K, V,D> extends BaseMapperImpl<K, V, D> implements BagInsertMapper<K, V,D> {
	
	@Override
	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	public void bagInsertStringMapper(HashMap<K, V> insertbagMap, StringBuilder persistData) {
        
		 
		 /******** Bag Column values retrieve from JSON Map **********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertbagMap);
		 V mongoId=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 
		 V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.BAG_SEAL_NUMBER, insertbagMap);
		 V codValue=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.COD_VALUE, insertbagMap);
		
		 V destinationCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DESTINATION_CENTER, insertbagMap);
		 V dimBreadth=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_BREADTH, insertbagMap);
		 V dimWeight=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_WEIGHT, insertbagMap);
		 V dimHeight=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_HEIGHT, insertbagMap);
		 V dimLength=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_LENGTH, insertbagMap);
		 V dimSummationWeight=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_SUMMATION_WEIGTH, insertbagMap);
		 V dimVolumetricWeight=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.DIM_VOLUMETRIC_WEIGHT, insertbagMap);
		 V firstPendingDate=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.FIRST_PENDING_DATE, insertbagMap);
		 V isComplete=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.IS_COMPLETE, insertbagMap);
		 V lastUpdatedBy=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.LAST_UPDATED_BY, insertbagMap);
		 V originCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.ORIGIN_CENTER, insertbagMap);
		 V parentBagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.PARENT_BAG_SEAL_NUMBER, insertbagMap);
		 V station=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.STATION, insertbagMap);
		 V totalValue=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.TOTAL_VALUE, insertbagMap);
		 
		 HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertbagMap);
		 V type=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		
		 V zone=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.ZONE, insertbagMap);
		 
		 HashMap<K, V> createdDateMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.CREATED_DATE, insertbagMap);
		 V createdDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, createdDateMap);
		 
		 
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.UPDATED_DATE, insertbagMap);
		 V updatedDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updateDateMap);
	
		 /******* Bag Table Column wise value is put in a StringBuilder *********/
		 persistData.append(mongoId.toString());
		 persistData.append(dimBreadth.toString());
		 persistData.append(dimHeight.toString());
		 persistData.append(dimLength.toString());
		 
		 persistData.append(destinationCenter.toString());
		 persistData.append(isComplete.toString());
		 persistData.append(dimWeight.toString());
		 
		 persistData.append(dimSummationWeight.toString());
		 persistData.append(dimVolumetricWeight.toString());
		 persistData.append(totalValue.toString());
		 
		 persistData.append(type.toString());
		 persistData.append(bagSealNumber.toString());
		 
		 persistData.append(station.toString());
		 persistData.append(originCenter.toString());
		 persistData.append(lastUpdatedBy.toString());
		 
		 persistData.append(zone.toString());
		 persistData.append(parentBagSealNumber.toString());
		 persistData.append(codValue.toString());
		 
		 persistData.append((createdDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?createdDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 persistData.append(createdDate.toString());
		 
		 persistData.append((updatedDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?updatedDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 persistData.append(updatedDate.toString());
		 
		 persistData.append((firstPendingDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?updatedDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 persistData.append(firstPendingDate.toString());
		 
		 persistData.append(operation);
		 persistData.append(DelhiveryEtlConstants.NEW_LINE);

		
	}
	@Override
	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	public void bagInsertMapper(HashMap<K, V> insertbagMap, HashMap<K, V> arrayList) {
	
		 /******* Bag Column values retrieve from JSON Map *********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertbagMap);
		 V mongoId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 
		 V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.BAG_SEAL_NUMBER, insertbagMap);
		 V codValue=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.COD_VALUE, insertbagMap);
		
		 V destinationCenter=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DESTINATION_CENTER, insertbagMap);
		 V dimBreadth=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_BREADTH, insertbagMap);
		 V dimWeight=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_WEIGHT, insertbagMap);
		 V dimHeight=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_HEIGHT, insertbagMap);
		 V dimLength=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_LENGTH, insertbagMap);
		 V dimSummationWeight=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_SUMMATION_WEIGTH, insertbagMap);
		 V dimVolumetricWeight=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.DIM_VOLUMETRIC_WEIGHT, insertbagMap);
		 V firstPendingDate=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.FIRST_PENDING_DATE, insertbagMap);
		 V isComplete=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.IS_COMPLETE, insertbagMap);
		 V lastUpdatedBy=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.LAST_UPDATED_BY, insertbagMap);
		 V originCenter=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.ORIGIN_CENTER, insertbagMap);
		 V parentBagSealNumber=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.PARENT_BAG_SEAL_NUMBER, insertbagMap);
		 V station=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.STATION, insertbagMap);
		 V totalValue=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.TOTAL_VALUE, insertbagMap);
		 
		 
		 HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertbagMap);
		 V type=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		
		
		
		 V zone=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.ZONE, insertbagMap);
		 
		 HashMap<K, V> createdDateMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.CREATED_DATE, insertbagMap);
		 V createdDate=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.CREATED_DATE, createdDateMap);
		 
		 
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.UPDATED_DATE, insertbagMap);
		 V updatedDate=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.UPDATED_DATE, updateDateMap);
	
		 /******* Bag Table Column wise value is put in a Map *********/
		 arrayList.put((K)DelhiveryEtlKeys.DB_MONGO_ID, mongoId);
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_BREADTH, dimBreadth);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_HEIGHT, dimHeight);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_LENGTH, dimLength);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_WEIGHT, dimWeight);
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DESTINATION_CENTER, destinationCenter);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_IS_COMPLETE, isComplete);
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_SUMMATION_WEIGTH, dimSummationWeight);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_DIM_VOLUMETRIC_WEIGHT, dimVolumetricWeight);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_TOTAL_VALUE, totalValue);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_TYPE, type);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_BAG_SEAL_NUMBER, bagSealNumber);
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_STATION, station);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_ORIGIN_CENTER, originCenter);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_LAST_UPDATED_BY, lastUpdatedBy);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_ZONE, zone);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_PARENT_BAG_SEAL_NUMBER, parentBagSealNumber);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_COD_VALUE, codValue);
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_CREATED_DATETIME, createdDate);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_CREATED_DATE,(V) ((!DelhiveryUtils.isNULL(createdDate))?createdDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_UPDATED_DATETIME, updatedDate);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_UPDATED_DATE, (V)((!DelhiveryUtils.isNULL(updatedDate))?updatedDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
		 
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_FIRST_PENDING_DATETIME, firstPendingDate);
		 arrayList.put((K)DelhiveryBagKeyAndColumn.DB_FIRST_PENDING_DATE, (V)((!DelhiveryUtils.isNULL(firstPendingDate))?firstPendingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
		 arrayList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	
	}

}
