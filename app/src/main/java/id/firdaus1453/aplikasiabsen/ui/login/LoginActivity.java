package id.firdaus1453.aplikasiabsen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.firdaus1453.aplikasiabsen.data.remote.ApiClient;
import id.firdaus1453.aplikasiabsen.data.remote.ApiInterface;
import id.firdaus1453.aplikasiabsen.model.login.LoginData;
import id.firdaus1453.aplikasiabsen.model.login.LoginResponse;
import id.firdaus1453.aplikasiabsen.ui.MainActivity;
import id.firdaus1453.aplikasiabsen.databinding.ActivityLoginBinding;
import id.firdaus1453.aplikasiabsen.utils.Constants;
import id.firdaus1453.aplikasiabsen.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private ProgressDialog progressDialog;
    private final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkLogin(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doLogin(binding.edtUsername.getText().toString(), binding.edtPassword.getText().toString());
            }
        });
    }

    public void checkLogin(Context context) {
        // Membuat object SessionManager
        mSessionManager = new SessionManager(context);
        // Mengambil data KEY_IS_LOGIN lalu memasukkan ke dalam variable isLogin
        boolean isLogin = mSessionManager.isLogin();
        // Mengecek apakah KEY_IS_LOGIN bernilai true
        if (isLogin){
            // Menyuruh view untuk melakukan perpindahan ke MainAcivity
            isLogin();
        }
    }

    public void isLogin() {
        // Berpindah halaman apabila user sudah login
        startActivity(new Intent(this, MainActivity.class));
        // Menutup loginActivity
        finish();
    }

    public void doLogin(String username, String password) {
        // Mencek username dan password
        if (username.isEmpty()) {
            usernameError("Username is empty");
            return;
        }

        if (password.isEmpty()){
            passwordError("Password is empty");
            return;
        }

        showProgress();

        Call<LoginResponse> call = apiInterface.loginUser(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                hideProgress();
                if (response.body() != null){
                    if (response.body().getResult() == 1){
                        if (response.body().getData() != null){
                            LoginData loginData = response.body().getData();
                            String message = response.body().getMessage();
                            loginSuccess(message, loginData);
                        }else {
                            loginFailure("Data tidak ada");
                        }
                    }else {
                        loginFailure(response.body().getMessage());
                    }
                }else {
                    loginFailure("Data tidak ada");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                hideProgress();
                loginFailure(throwable.getMessage());
            }
        });
    }

    public void loginSuccess(String message, LoginData loginData) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Menyimpan data user ke dalam SharedPreference
        saveDataUser(this, loginData);

        LoginData mLoginData = new LoginData();
        mLoginData.setIdUser(loginData.getIdUser());
        mLoginData.setName(loginData.getName());
        mLoginData.setUsername(loginData.getUsername());
        mLoginData.setLevel(loginData.getLevel());

        startActivity(new Intent(this, MainActivity.class).putExtra(Constants.KEY_LOGIN, mLoginData));
        finish();
    }

    public void loginFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void saveDataUser(Context context, LoginData loginData) {
        // Membuat object SessionManager
        mSessionManager = new SessionManager(context);
        // Mensave data ke SharedPreference dengan menggunakan method dari class SessionManager
        mSessionManager.createSession(loginData);
    }


    public void usernameError(String message) {
        binding.edtUsername.setError(message);
        binding.edtUsername.setFocusable(true);
    }

    public void passwordError(String message) {
        binding.edtPassword.setError(message);
        binding.edtPassword.setFocusable(true);
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgress() {
        progressDialog.dismiss();
    }
}