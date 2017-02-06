package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.dispatch.DispatchCurrencyCountInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchCurrencyCountInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D> implements DispatchCurrencyCountInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchCurrencyCountInsertMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> arrayList) {
		 V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		 
		 HashMap<K, V> currencyMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CURRENCY, insertDispatchMap);
		 
		 V one=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ONE, currencyMap);
		 V two=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TWO, currencyMap);
		 V five=delhiveryCollection.getValue((K)DelhiveryEtlKeys.FIVE, currencyMap);
		 V ten=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TEN, currencyMap);
		 V twenty=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TWENTY, currencyMap);
		 V fifty=delhiveryCollection.getValue((K)DelhiveryEtlKeys.FIFTY, currencyMap);
		 V hundred=delhiveryCollection.getValue((K)DelhiveryEtlKeys.HUNDRED, currencyMap);
		 V fiveHundred=delhiveryCollection.getValue((K)DelhiveryEtlKeys.FIVE_HUNDRED, currencyMap);
		 V thousand=delhiveryCollection.getValue((K)DelhiveryEtlKeys.THOUSAND, currencyMap);
	
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		 V updateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 arrayList.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		 arrayList.put((K)DelhiveryEtlKeys.DB_ONE, one);
		 arrayList.put((K)DelhiveryEtlKeys.DB_TWO, two);
		 arrayList.put((K)DelhiveryEtlKeys.DB_FIVE, five);
		 arrayList.put((K)DelhiveryEtlKeys.DB_TEN, ten);
		
		 arrayList.put((K)DelhiveryEtlKeys.DB_TWENTY, twenty);
		 arrayList.put((K)DelhiveryEtlKeys.DB_TWENTY, twenty);
		 arrayList.put((K)DelhiveryEtlKeys.DB_FIFTY, fifty);
		 arrayList.put((K)DelhiveryEtlKeys.DB_HUNDRED, hundred);
		 arrayList.put((K)DelhiveryEtlKeys.DB_FIVE_HUNDRED, fiveHundred);
		 arrayList.put((K)DelhiveryEtlKeys.DB_THOUSAND, thousand);
		 arrayList.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, updateDate);
		 arrayList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchCurrencyCountInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		 V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		 
		 HashMap<K, V> currencyMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CURRENCY, insertDispatchMap);
		 
		 V one=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ONE, currencyMap);
		 V two=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.TWO, currencyMap);
		 V five=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.FIVE, currencyMap);
		 V ten=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.TEN, currencyMap);
		 V twenty=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.TWENTY, currencyMap);
		 V fifty=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.FIFTY, currencyMap);
		 V hundred=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.HUNDRED, currencyMap);
		 V fiveHundred=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.FIVE_HUNDRED, currencyMap);
		 V thousand=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.THOUSAND, currencyMap);
	
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		 V updateDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(one.toString());
		 insertString.append(two.toString());
		 insertString.append(five.toString());
		 insertString.append(ten.toString());
		 
		 insertString.append(twenty.toString());
		 insertString.append(fifty.toString());
		 insertString.append(hundred.toString());
		 insertString.append(fiveHundred.toString());
		 insertString.append(thousand.toString());
		 insertString.append(updateDate.toString());
		 insertString.append(operation);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		
	}


}
