/**
 * 
 */
package com.marketplace.entity;

/**
 * @author arnab
 *
 */
public class User {
	
	private String userName;
	
	public User() {}
	
	public User(String userName) {
		this.userName = userName;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equalsIgnoreCase(other.userName))
			return false;
		return true;
	}
	
	

}
