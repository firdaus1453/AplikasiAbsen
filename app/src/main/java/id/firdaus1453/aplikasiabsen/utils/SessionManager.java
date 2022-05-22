package id.firdaus1453.aplikasiabsen.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.firdaus1453.aplikasiabsen.model.login.LoginData;
import id.firdaus1453.aplikasiabsen.ui.login.LoginActivity;

/**
 * Created by firdaus1453 on 2/28/2019.
 */
public class SessionManager {
    // Membuat varibale global untuk shared preference
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final Context context;

    public SessionManager(Context context) {
        this.context = context;
        // Membuat object SharedPreference untuk siap digunakan
        pref = context.getSharedPreferences(Constants.pref_name, 0);
        // Membuat SharedPreference dengan mode edit
        editor = pref.edit();
    }

    // Function untuk membuat session login
    public void createSession(LoginData loginData){
        // memasukkan data user yang sudah login ke dalam SharedPreference
        editor.putBoolean(Constants.KEY_IS_LOGIN, true);
        editor.putString(Constants.KEY_USER_ID, loginData.getIdUser());
        editor.putString(Constants.KEY_NAME, loginData.getName());
        editor.putString(Constants.KEY_USER_USERNAME, loginData.getUsername());
        editor.putString(Constants.KEY_USER_LEVEL, loginData.getLevel());
        // Mengeksekusi penyimpanan
        editor.commit();
    }

    // Function untuk mencek apakah user sudah pernah login
    public boolean isLogin(){
        // Mengembalikan nilai boolean dengan mengambil data dari pref KEY_IS_LOGIN
        return pref.getBoolean(Constants.KEY_IS_LOGIN, false);
    }

    // Function untuk melakukan logout atau menghapus isi di dalam shared preference
    public void logout(){
        // Memanggil method clear untuk menghapus data sharedpreference
        editor.clear();
        // Mengeksekusi perintah clear
        editor.commit();
        // Membuat intent untuk berpindah halaman
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
