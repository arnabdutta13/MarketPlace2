/**
 * 
 */
package com.marketplace.service;

/**
 * @author arnab
 *
 */
public interface IListingService extends IMarketPlaceService {

	/**
	 * Creates a listing
	 * @param userName
	 * @param title
	 * @param description
	 * @param price
	 * @param category
	 * @return
	 * @throws Exception
	 */
	String createListing(String userName, String title, String description, Double price, String category) throws Exception;

	/**
	 * Deletes a listing
	 * @param userName
	 * @param listingId
	 * @return
	 * @throws Exception
	 */
	String deleteListing(String userName, String listingId) throws Exception;

	/**
	 * Gets listing by ID
	 * @param userName
	 * @param listingId
	 * @return
	 * @throws Exception
	 */
	String getListing(String userName, String listingId) throws Exception;

}
