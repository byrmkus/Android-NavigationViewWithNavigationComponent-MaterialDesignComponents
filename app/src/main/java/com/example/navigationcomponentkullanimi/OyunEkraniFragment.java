package com.example.navigationcomponentkullanimi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationcomponentkullanimi.databinding.FragmentOyunEkraniBinding;
import com.example.navigationcomponentkullanimi.viewmodel.SharedViewModel;

public class OyunEkraniFragment extends Fragment {
    private Button buttonBitir;
    private FragmentOyunEkraniBinding binding;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOyunEkraniBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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

        binding.buttonBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.sonucEkraninaGecis);
                sendNotification("Süper");
            }
        });

        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.countryLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewCountry.setText(s);
            }
        });

    }

    public void sendNotification(String message) {
        NotificationCompat.Builder builder;

        NotificationManager bildirimYoneticisi =
                (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);

        Intent ıntent = new Intent(getContext(), SonucEkraniFragment.class);


        PendingIntent pendingIntent = new NavDeepLinkBuilder(getContext())
                .setGraph(R.navigation.main_activity_nav)
                .setDestination(R.id.anasayfaFragment)
                //.setArguments(args)
                .createPendingIntent();

       // PendingIntent gidilecekIntent = PendingIntent.getActivity(getContext(), 1, ıntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Oreo sürümü için bu kod çalışacak.

            String kanalId = "kanalId";
            String kanalAd = "kanalAd";
            String kanalTanım = "kanalTanım";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);

            if (kanal == null) {
                kanal = new NotificationChannel(kanalId, kanalAd, kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(getContext(), kanalId).setContentIntent(pendingIntent);

            builder.setContentTitle("AYBS İS EMRİ")  // gerekli
                    .setContentText(message)  // gerekli
                    .setSmallIcon(R.drawable.ic_menu) // gerekli
                    .setAutoCancel(true)  // Bildirim tıklandıktan sonra kaybolur."
                    .setContentIntent(pendingIntent);

        } else { // OREO Sürümü haricinde bu kod çalışacak.

            builder = new NotificationCompat.Builder(getContext()).setContentIntent(pendingIntent);

            builder.setContentTitle("AYBS IS EMRİ")  // gerekli
                    .setContentText(message)  // gerekli
                    .setSmallIcon(R.drawable.ic_launcher_background) // gerekli
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)  // Bildirim tıklandıktan sonra kaybolur."
                    .setPriority(Notification.PRIORITY_HIGH);
        }

        bildirimYoneticisi.notify(1, builder.build());
    }

}
