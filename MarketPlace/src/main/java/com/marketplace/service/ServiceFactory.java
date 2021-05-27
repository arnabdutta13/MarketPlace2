/**
 * 
 */
package com.marketplace.service;

import com.marketplace.exception.MarketPlaceException;

/**
 * @author arnab
 *
 */
public class ServiceFactory {
	
	public static IMarketPlaceService getService(String type) throws Exception {
		switch (type) {
		case "USER": 
			return UserMgmtServiceImpl.getInstance();
		case "LISTING": 
			return ListingServiceImpl.getInstance();
		case "CATEGORY": 
			return CategoryServiceImpl.getInstance();	
		default:
			throw new MarketPlaceException("No servcie defined for : " + type);
		}
	}

}
