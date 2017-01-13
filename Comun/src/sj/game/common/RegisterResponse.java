package sj.game.common;

public class RegisterResponse extends ServerResponse{
	private static final long serialVersionUID = 7830765645797191168L;
	
	public static final int REGISTER_OK = 1;
	public static final int REGISTER_FAILED_USERNAME_TAKEN = 2;
	
	private final int result;

	public RegisterResponse(RegisterRequest m, int result) {
		super(m);
		this.result = result;
	}

	public int getResult() {
		return result;
	}

}
