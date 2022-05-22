package id.firdaus1453.aplikasiabsen.data.remote;

import id.firdaus1453.aplikasiabsen.model.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by firdaus1453 on 2/28/2019.
 */
public interface ApiInterface {

    // Membuat endpoint login
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
}
