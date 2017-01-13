package sj.game.common;

public class RegisterRequest extends ClientMessage{
	private static final long serialVersionUID = -3323950655746784476L;
	private final String username;
	private final String password;

	public RegisterRequest(String username, String password) {
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
