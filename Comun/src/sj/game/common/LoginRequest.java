package sj.game.common;

public class LoginRequest extends ClientMessage {
	private static final long serialVersionUID = 930655732020464623L;

	private final String username;
	private final String password;
	
	public LoginRequest(String username, String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
