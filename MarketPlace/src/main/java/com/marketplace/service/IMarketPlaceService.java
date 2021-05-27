/**
 * 
 */
package com.marketplace.service;

import com.marketplace.entity.Command;

/**
 * @author arnab
 *
 */
public interface IMarketPlaceService {
	/**
	 * Processes the command entered by the user
	 * @param c
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Object process(Command c, String[] params) throws Exception;

	/**
	 * Validated the command and its parameters
	 * @param c
	 * @param params
	 * @throws Exception
	 */
	void validateCommand(Command c, String[] params) throws Exception;

}
