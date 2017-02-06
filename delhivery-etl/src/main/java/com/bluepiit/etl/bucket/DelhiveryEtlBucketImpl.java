package com.bluepiit.etl.bucket;

import java.util.Date;

import com.bluepiit.etl.utils.common.DelhiveryEtlDateUtil;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;

public class DelhiveryEtlBucketImpl implements FileName,KeyName {

	@Override
	public String keyName() {
		return DelhiveryEtlDateUtil.format(new Date(), DelhiveryEtlConstants.TD_DATE_TIME_FORMAT_NO_SECONDS);
	}

	@Override
	public String fileName() {
	    return String.valueOf(DelhiveryEtlDateUtil.getUtcTime())+ DelhiveryEtlConstants.JSON_EXTENSIION;	
	}

}
