/**
 * 
 */
package com.marketplace.service;

import java.util.List;

import com.marketplace.data.GroupFreqCache;
import com.marketplace.data.MarketPlaceData;
import com.marketplace.entity.Command;
import com.marketplace.entity.Listing;
import com.marketplace.exception.MarketPlaceException;

/**
 * @author arnab
 *
 */
public class CategoryServiceImpl implements ICategoryService {

	private static CategoryServiceImpl categoryService = new CategoryServiceImpl();
	
	private MarketPlaceData dataService;

	private GroupFreqCache cache;
	
	private CategoryServiceImpl() {
		this.dataService = MarketPlaceData.getInstance();
		this.cache = GroupFreqCache.getInstance();
	}
	
	public static CategoryServiceImpl getInstance() {
		return categoryService;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return categoryService;
	}
	
	/* (non-Javadoc)
	 * @see com.marketplace.service.IMarketPlaceService#process(com.marketplace.entity.Command, java.lang.String[])
	 */
	@Override
	public Object process(Command c, String[] params) throws Exception {
		validateCommand(c, params);
		switch(c.getCmd()) { 
			case "GET_CATEGORY": 
				if (params.length == 3) {
					return this.getCategory(params[1], params[2], null, null);
				}
				if (params.length == 4) {
					return this.getCategory(params[1], params[2], params[3], "asc");
				}
				if (params.length == 5) {
					return this.getCategory(params[1], params[2], params[3], params[4]);
				}
				
			case "GET_TOP_CATEGORY":
				return this.getTopCategory(params[1], 1);
			default:
				return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IMarketPlaceService#validateCommand(com.marketplace.entity.Command, java.lang.String[])
	 */
	@Override
	public void validateCommand(Command c, String[] params) throws Exception {
		if (c.getMinParams() + 1 > params.length) {
			throw new MarketPlaceException("Error - Invalid params for " + c.getCmd());
		}
		if (!params[1].matches("^[a-zA-Z0-9]+$"))  {
			throw new MarketPlaceException("Error - username must be alphanumeric");
		}
		if (!this.dataService.getUserData().containsKey(params[1].toLowerCase())) {
			throw new MarketPlaceException("Error - unknown user");
		}

	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.ICategoryService#getCategory(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getCategory(String userName, String category, String sortFieldName, String sortOrder)
			throws Exception {
		if (!this.dataService.getCategoryData().containsKey(category)) {
			throw new MarketPlaceException("Error - category not found");
		}
		List<Listing> listing = null;
		if (sortFieldName != null && !"sort_price".equals(sortFieldName) && !"sort_time".equals(sortFieldName)) {
			throw new MarketPlaceException("Error - invalid sort fields");
		}
		if (sortOrder != null && !"asc".equals(sortOrder) && !"dsc".equals(sortOrder)) {
			throw new MarketPlaceException("Error - invalid sort roder");
		}
		if (sortFieldName != null) {
			listing = this.dataService.getCategoryData().get(category).sort(sortFieldName, sortOrder);
		} else {
			listing = this.dataService.getCategoryData().get(category).getProducts();
		}
		if (listing == null || listing.isEmpty()) {
			throw new MarketPlaceException("Error - category not found");
		}
		
		StringBuffer sb = new StringBuffer();
		for (Listing l  : listing) {
			sb.append(l.toString() + "\n");
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.ICategoryService#getTopCategory(java.lang.String)
	 */
	@Override
	public String getTopCategory(String userName, int num) throws Exception {
		return this.cache.getTop(num);
	}

}
