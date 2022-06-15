package com.example.navigationcomponentkullanimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OyunEkraniFragment extends Fragment {
    private Button buttonBitir;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tasarim = inflater.inflate(R.layout.fragment_oyun_ekrani, container, false);

        buttonBitir = tasarim.findViewById(R.id.buttonBitir);

        OyunEkraniFragmentArgs bundle = OyunEkraniFragmentArgs.fromBundle(getArguments());
        String gelenAd = bundle.getAd();
        int gelenYas = bundle.getYas();
        float gelenBoy = bundle.getBoy();
        boolean gelenBekarMi = bundle.getBekarMi();
        Kisiler gelenKisi = bundle.getNesne();

        Log.e("Gelen Ad",gelenAd);
        Log.e("Gelen Yaş",String.valueOf(gelenYas));
        Log.e("Gelen Boy",String.valueOf(gelenBoy));
        Log.e("Gelen Bekar Mı",String.valueOf(gelenBekarMi));
        Log.e("Gelen Kişi No",String.valueOf(gelenKisi.getKisi_no()));
        Log.e("Gelen Kişi Ad",gelenKisi.getKisi_ad());

        buttonBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.sonucEkraninaGecis);
            }
        });

        return tasarim;
    }
}