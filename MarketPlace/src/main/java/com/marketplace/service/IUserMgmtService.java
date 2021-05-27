/**
 * 
 */
package com.marketplace.service;

import com.marketplace.entity.User;

/**
 * @author arnab
 *
 */
public interface IUserMgmtService extends IMarketPlaceService  {

	/**
	 * registers a user
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	String registerUser(String userName) throws Exception;

	/**
	 * gets existing user by user name. returns null if not present
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	User getUserByUserName(String userName) throws Exception;

}
