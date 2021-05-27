/**
 * 
 */
package com.marketplace.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author arnab
 *
 */
public class Listing {
	
	private String listingId;
	
	private String title;
	
	private String description;
	
	private Double price;
	
	private String userName;
	
	private Date creationTime;
	
	private String category;
	
	public Listing() {}

	public Listing(String listingId, String title, String description, Double price, String userName, Date creationTime,
			String category) {
		this.listingId = listingId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.userName = userName;
		this.creationTime = creationTime;
		this.category = category;
	}

	public String getListingId() {
		return listingId;
	}

	public void setListingId(String listingId) {
		this.listingId = listingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listingId == null) ? 0 : listingId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Listing other = (Listing) obj;
		if (listingId == null) {
			if (other.listingId != null)
				return false;
		} else if (!listingId.equals(other.listingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		return title + " | " + description + " | " + price  + " | " + sf.format(creationTime) + " | " + category + " | " + userName;
		
	}
	
	

}
