package xyz.eveneer.sadmansakib.kuul.Splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
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
        mHandler.postDelayed(() -> splashViewModel.launchOTP(getApplicationContext(),this),SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(()-> splashViewModel.launchOTP(getApplicationContext(),this));
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        splashViewModel.checkOTPStatus(getApplicationContext(),requestCode,data);
    }
}
