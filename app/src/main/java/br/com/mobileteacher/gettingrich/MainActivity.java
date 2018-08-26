package br.com.mobileteacher.gettingrich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView bannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        bannerView = findViewById(R.id.banner_view);
        //Necessário carregar uma requisição de Ads
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerView.loadAd(adRequest);
    }
}
