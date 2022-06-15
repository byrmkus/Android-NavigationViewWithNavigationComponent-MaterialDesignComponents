package com.example.navigationcomponentkullanimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AnasayfaFragment extends Fragment {
    private Button buttonBasla;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tasarim = inflater.inflate(R.layout.fragment_anasayfa, container, false);

        buttonBasla = tasarim.findViewById(R.id.buttonBasla);

        buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kisiler kisi = new Kisiler(1,"Mehmet");
                AnasayfaFragmentDirections.OyunEkraninaGecis gecis = AnasayfaFragmentDirections.oyunEkraninaGecis(kisi);
                gecis.setAd("Ahmet");
                gecis.setYas(18);
                gecis.setBoy(1.78f);
                gecis.setBekarMi(true);

                Navigation.findNavController(v).navigate(gecis);
            }
        });

        return tasarim;
    }
}