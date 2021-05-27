/**
 * 
 */
package com.marketplace.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marketplace.data.MarketPlaceData;
import com.marketplace.entity.Category;
import com.marketplace.entity.Command;
import com.marketplace.entity.Listing;
import com.marketplace.exception.MarketPlaceException;

/**
 * @author arnab
 *
 */
public class ListingServiceImpl implements IListingService {
	
	private static ListingServiceImpl listingService = new ListingServiceImpl();
	
	private MarketPlaceData dataService;
	
	private ListingServiceImpl() {
		this.dataService = MarketPlaceData.getInstance();
	}
	
	public static ListingServiceImpl getInstance() {
		return listingService;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return listingService;
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IMarketPlaceService#process(com.marketplace.entity.Command, java.lang.String[])
	 */
	@Override
	public Object process(Command c, String[] params) throws Exception {
		validateCommand(c, params);
		switch(c.getCmd()) { 
			case "CREATE_LISTING": 
				return this.createListing(params[1], params[2], params[3], new Double(params[4]), params[5]);
			case "DELETE_LISTING":
				return this.deleteListing(params[1], params[2]);
			case "GET_LISTING":
				return this.getListing(params[1], params[2]);
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
		if ("DELETE_LISTING".equals(c.getCmd()) || "GET_LISTING".equals(c.getCmd())) {
			if (!params[2].matches("[0-9]{1,}"))  {
				throw new MarketPlaceException("Error - listing id must be numeric");
			}
		}
		if ("CREATE_LISTING".equals(c.getCmd())) {
			try {
				Double.parseDouble(params[4]);
			} catch (NumberFormatException e) {
				throw new MarketPlaceException("Error - enter valid price");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IListingService#createListing(java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.String)
	 */
	@Override
	public String createListing(String userName, String title, String description, Double price, String category)
			throws Exception {
		
		Listing listing = new Listing(this.dataService.getListingId() + "", title, description, price, userName, new Date(), category);
		if (this.dataService.getListingData().containsKey(listing.getListingId() + "")) {
			throw new MarketPlaceException("Error - Listing ID already exists");
		}
		this.dataService.getListingData().put(listing.getListingId() + "", listing);
		if (this.dataService.getCategoryData().containsKey(category)) {
			this.dataService.getCategoryData().get(category).getProducts().add(listing);
		} else {
			List<Listing> list = new ArrayList<>();
			list.add(listing);
			this.dataService.getCategoryData().put(category, new Category(category, list));
		}
		
		Category cat = new Category(category, 0);
		if (this.dataService.getCategoryFreqDataCache().containsKey(cat)) {
			cat.setCount(this.dataService.getCategoryFreqDataCache().get(cat) + 1);
			this.dataService.getCategoryFreqDataCache().put(cat, cat.getCount());
		} else {
			cat.setCount(1);
			this.dataService.getCategoryFreqDataCache().put(cat, 1);
		}
		return listing.getListingId() + "";
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IListingService#deleteListing(java.lang.String, java.lang.String)
	 */
	@Override
	public String deleteListing(String userName, String listingId) throws Exception {
		if (!this.dataService.getListingData().containsKey(listingId)) {
			throw new MarketPlaceException("Error - listing does not exist");
		}
		Listing l = this.dataService.getListingData().get(listingId);
		if (!l.getUserName().equalsIgnoreCase(userName)) {
			throw new MarketPlaceException("Error - listing owner mismatch");
		}
		this.dataService.getListingData().remove(listingId);
		
		this.dataService.getCategoryData().get(l.getCategory()).getProducts().remove(l);
		
		Category cat = new Category(l.getCategory(), 0);
		if (this.dataService.getCategoryFreqDataCache().containsKey(cat)) {
			cat.setCount(this.dataService.getCategoryFreqDataCache().get(cat) - 1);
			this.dataService.getCategoryFreqDataCache().put(cat, cat.getCount());
		}
		return "Success";
	}

	/* (non-Javadoc)
	 * @see com.marketplace.service.IListingService#getListing(java.lang.String, java.lang.String)
	 */
	@Override
	public String getListing(String userName, String listingId) throws Exception {
		if (!this.dataService.getListingData().containsKey(listingId)) {
			throw new MarketPlaceException("Error - not found");
		}
	
		return this.dataService.getListingData().get(listingId).toString();
	}

}
