package id.firdaus1453.aplikasiabsen.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginData implements Parcelable {

	@SerializedName("level")
	private String level;

	@SerializedName("name")
	private String name;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("username")
	private String username;

	public String getLevel(){
		return level;
	}

	public String getName(){
		return name;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getUsername(){
		return username;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.level);
		dest.writeString(this.name);
		dest.writeString(this.idUser);
		dest.writeString(this.username);
	}

	public void readFromParcel(Parcel source) {
		this.level = source.readString();
		this.name = source.readString();
		this.idUser = source.readString();
		this.username = source.readString();
	}

	public LoginData() {
	}

	protected LoginData(Parcel in) {
		this.level = in.readString();
		this.name = in.readString();
		this.idUser = in.readString();
		this.username = in.readString();
	}

	public static final Parcelable.Creator<LoginData> CREATOR = new Parcelable.Creator<LoginData>() {
		@Override
		public LoginData createFromParcel(Parcel source) {
			return new LoginData(source);
		}

		@Override
		public LoginData[] newArray(int size) {
			return new LoginData[size];
		}
	};
}