package com.ekar.counter.helper;

import com.ekar.counter.exception.EKARException;
import com.ekar.counter.exception.EKARExceptionConstants;

public class EKARCounterHelper {

	private EKARCounterHelper() {}
	
	public static void checkRequestParams(int... requestParams) throws EKARException {
		for(int i=0;i<requestParams.length;i++) {
			if(requestParams[i]<0)
				throw new EKARException(EKARExceptionConstants.REQUEST_VALUES_CANNOT_BE_LESS_THAN_ZERO);
		}
	}
}
