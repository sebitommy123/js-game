package sj.game.common;

public class LoginResponse extends ServerResponse {
	private static final long serialVersionUID = 5734111137485538619L;
	private final boolean accepted;

	public LoginResponse(LoginRequest m, boolean accepted) {
		super(m);
		this.accepted = accepted;
	}

	public boolean isAccepted() {
		return accepted;
	}

}
