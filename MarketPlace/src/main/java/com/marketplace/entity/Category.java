/**
 * 
 */
package com.marketplace.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author arnab
 *
 */
public class Category implements Comparable<Category> {
	
	private String name;
	
	private List<Listing> products;
	
	private Integer count;
	
	public Category() {}

	public Category(String name, List<Listing> products) {
		this.name = name;
		this.products = products;
	}

	public Category(String name, Integer count) {
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Listing> getProducts() {
		return products;
	}

	public void setProducts(List<Listing> products) {
		this.products = products;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Sorts the listings based on field and order
	 * @param fieldName
	 * @param order
	 * @return
	 */
	public List<Listing> sort(String fieldName, String order) {
		if (this.products == null || this.products.isEmpty()) {
			return null;
		}
		List<Listing> list = new ArrayList<>(this.products.size());
		this.products.forEach( x -> list.add(x));
		list.sort(new Comparator<Listing>() {

			@Override
			public int compare(Listing o1, Listing o2) {
				if ("sort_price".equals(fieldName)) {
					return "asc".equalsIgnoreCase(order) ? o1.getPrice().compareTo(o2.getPrice()) : 0 - o1.getPrice().compareTo(o2.getPrice());
				}
				if ("sort_time".equals(fieldName)) {
					return "asc".equalsIgnoreCase(order) ? o1.getCreationTime().compareTo(o2.getCreationTime()) : 0 - o1.getCreationTime().compareTo(o2.getCreationTime());
				}	
				return 0;
			}
			
		});
		return list;
	}

	@Override
	public int compareTo(Category o) {
		//return 0 - this.count.compareTo(o.getCount());
		if (this.name.equals(o.getName())) {
			return 0 - this.count.compareTo(o.getCount());
		}
		return this.count > o.getCount() ? -1 : 1;

	}

}
