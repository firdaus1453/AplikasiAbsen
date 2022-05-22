package id.firdaus1453.aplikasiabsen.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("result")
	private int result;

	@SerializedName("data")
	private LoginData loginData;

	@SerializedName("message")
	private String message;

	public int getResult(){
		return result;
	}

	public LoginData getData(){
		return loginData;
	}

	public String getMessage(){
		return message;
	}
}