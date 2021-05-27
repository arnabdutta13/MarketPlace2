/**
 * 
 */
package com.marketplace.service;

import com.marketplace.data.MarketPlaceData;
import com.marketplace.entity.Command;
import com.marketplace.entity.User;
import com.marketplace.exception.MarketPlaceException;

/**
 * @author arnab
 *
 */
public class UserMgmtServiceImpl implements IUserMgmtService {
	
	private static UserMgmtServiceImpl userMgmtService = new UserMgmtServiceImpl();
	
	private MarketPlaceData dataService;
	
	private UserMgmtServiceImpl() {
		this.dataService = MarketPlaceData.getInstance();
	}
	
	public static UserMgmtServiceImpl getInstance() {
		return userMgmtService;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return userMgmtService;
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IUserMgmtService#registerUser(java.lang.String)
	 */
	@Override
	public String registerUser(String userName) throws Exception {
		if (dataService.getUserData().containsKey(userName.toLowerCase())) {
			throw new MarketPlaceException("Error - user already existing");
		}
		dataService.getUserData().put(userName.toLowerCase(), new User(userName.toLowerCase()));
		return "Success";
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IUserMgmtService#getUserByUserName(java.lang.String)
	 */
	@Override
	public User getUserByUserName(String userName) throws Exception {
		if (dataService.getUserData().containsKey(userName.toLowerCase())) {
			throw new MarketPlaceException("Error - unknown user");
		}
		return dataService.getUserData().get(userName.toLowerCase());
	}

	@Override
	public Object process(Command c, String[] params) throws Exception {
		validateCommand(c, params);
		switch(c.getCmd()) { 
			case "REGISTER": 
				return this.registerUser(params[1]);
			default:
				return null;
		}
	}

	@Override
	public void validateCommand(Command c, String[] params) throws Exception {
		if (c.getMinParams() + 1 > params.length) {
			throw new MarketPlaceException("Error - Invalid params for " + c.getCmd());
		}
		if (!params[1].matches("^[a-zA-Z0-9]+$"))  {
			throw new MarketPlaceException("Error - username must be alphanumeric");
		}
	}

}
