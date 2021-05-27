/**
 * 
 */
package com.marketplace.service;

/**
 * @author arnab
 *
 */
public interface ICategoryService extends IMarketPlaceService {

	/**
	 * Gets a category
	 * @param userName
	 * @param catrgory
	 * @param sortFieldName
	 * @param sortOrder
	 * @return
	 * @throws Exception
	 */
	String getCategory(String userName, String category, String sortFieldName, String sortOrder) throws Exception;

	/**
	 * Gets the top category from the cache
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	String getTopCategory(String userName) throws Exception;
}
