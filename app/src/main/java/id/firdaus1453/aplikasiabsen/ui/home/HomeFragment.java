package id.firdaus1453.aplikasiabsen.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.firdaus1453.aplikasiabsen.databinding.FragmentHomeBinding;
import id.firdaus1453.aplikasiabsen.utils.Constants;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getDataUser(binding.getRoot().getContext());
        return root;
    }

    public void getDataUser(Context context) {
        // Pengambilan data dari SharedPreference
        SharedPreferences pref = context.getSharedPreferences(Constants.pref_name,0);
        binding.textHome.setText(pref.getString(Constants.KEY_NAME,""));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}