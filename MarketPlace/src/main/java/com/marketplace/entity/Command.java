/**
 * 
 */
package com.marketplace.entity;

/**
 * @author arnab
 *
 */
public class Command {
	
	private String cmd;
	
	private int minParams;
	
	private String type;

	public Command(String cmd, int minParams, String type) {
		this.cmd = cmd;
		this.minParams = minParams;
		this.type = type;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int getMinParams() {
		return minParams;
	}

	public void setMinParams(int minParams) {
		this.minParams = minParams;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmd == null) ? 0 : cmd.hashCode());
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
		Command other = (Command) obj;
		if (cmd == null) {
			if (other.cmd != null)
				return false;
		} else if (!cmd.equals(other.cmd))
			return false;
		return true;
	}
	
	

}
