package sj.game.common;

import java.io.Serializable;

public class UserData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 601651317904294019L;
	private String username;
	private long uniqueID;
	private String password;
	public UserData(String username, String password, long id) {
		this.username = username;
		this.password = password;
		this.uniqueID = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(long uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
