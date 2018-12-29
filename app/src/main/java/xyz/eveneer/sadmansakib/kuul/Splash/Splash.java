package xyz.eveneer.sadmansakib.kuul.Splash;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xyz.eveneer.sadmansakib.kuul.R;

import static xyz.eveneer.sadmansakib.kuul.Constants.splash.SPLASH_DELAY;

public class Splash extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(() -> splashViewModel.launch(getApplicationContext(),this),SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(()-> splashViewModel.launch(getApplicationContext(),this));
        super.onStop();
    }

}
