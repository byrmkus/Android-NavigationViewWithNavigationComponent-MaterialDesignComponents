package com.example.navigationcomponentkullanimi;

import android.os.Bundle;

import androidx.activity.ActivityViewModelLazyKt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationcomponentkullanimi.databinding.FragmentAnasayfaBinding;
import com.example.navigationcomponentkullanimi.viewmodel.SharedViewModel;

public class AnasayfaFragment extends Fragment {
    private Button buttonBasla;
    private SharedViewModel sharedViewModel;
    private FragmentAnasayfaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kisiler kisi = new Kisiler(1, "Mehmet");
                AnasayfaFragmentDirections.OyunEkraninaGecis gecis = AnasayfaFragmentDirections.oyunEkraninaGecis(kisi);
                gecis.setAd("Ahmet");
                gecis.setYas(18);
                gecis.setBoy(1.78f);
                gecis.setBekarMi(true);
                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
                sharedViewModel.saveCountry(binding.editTextCountry.getText().toString());
                Navigation.findNavController(view).navigate(gecis);


            }
        });
    }
}