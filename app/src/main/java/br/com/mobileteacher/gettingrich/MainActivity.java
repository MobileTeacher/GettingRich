package br.com.mobileteacher.gettingrich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static com.google.android.gms.ads.AdRequest.ERROR_CODE_INTERNAL_ERROR;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_INVALID_REQUEST;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_NETWORK_ERROR;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_NO_FILL;

public class MainActivity extends AppCompatActivity {

    private AdView bannerView;
    private InterstitialAd intertistialView;
    private  AdView videoAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.admob_appid));

        bannerView = findViewById(R.id.banner_view);
        bannerView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                String errorName = "";
                switch (errorCode){
                    case ERROR_CODE_INTERNAL_ERROR:
                        errorName = "ERROR_CODE_INTERNAL_ERROR";
                        break;
                    case ERROR_CODE_INVALID_REQUEST:
                        errorName = "ERROR_CODE_INVALID_REQUEST";
                        break;
                    case ERROR_CODE_NETWORK_ERROR:
                        errorName = "ERROR_CODE_NETWORK_ERROR";
                        break;
                    case ERROR_CODE_NO_FILL:
                        errorName = "ERROR_CODE_NO_FILL";
                        break;
                }
                TextView textView = findViewById(R.id.message_text);
                textView.setText("Anúncio não exibido devido ao erro: " + errorName);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Toast.makeText(getApplicationContext(), "Banner bem sucedido", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        //Necessário carregar uma requisição de Ads
        //AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice("C611270B07AFD2901F19F505EF14BA9E").build();
        //após o load, o banner será exibido
        bannerView.loadAd(adRequest);


        intertistialView = new InterstitialAd(this);
        // estático
        //intertistialView.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //vídeo
        intertistialView.setAdUnitId("ca-app-pub-3940256099942544/8691691433");
        // após o load, o anúncio interticial não é exibido automaticamente
        intertistialView.loadAd(new AdRequest.Builder().build());

        intertistialView.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                intertistialView.loadAd(new AdRequest.Builder().build());
                startActivity(new Intent(getApplicationContext(), SuccessActivity.class));
            }
        });

    //I/Ads: Use AdRequest.Builder.addTestDevice("C611270B07AFD2901F19F505EF14BA9E") to get test ads on this device.
    }


    public void showInterstitial(View view){
        //Agora a gente pede manualmente para exibir o anúncio intersticial
        if (intertistialView.isLoaded()){
            intertistialView.show();
        }
    }

    public void showVideo(View view){

    }
}
