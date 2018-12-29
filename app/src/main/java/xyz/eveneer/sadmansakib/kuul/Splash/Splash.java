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
        mHandler.postDelayed(() -> splashViewModel.launchOTP(getApplicationContext(),this),SPLASH_DELAY);
        makeTransitionAnimation();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(()-> splashViewModel.launchOTP(getApplicationContext(),this));
        makeTransitionAnimation();
        super.onStop();
    }

    private void makeTransitionAnimation(){
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(splashViewModel.checkOTPStatus(getApplicationContext(), requestCode, data)){
            mHandler.postDelayed(() -> splashViewModel.launchSignUp(getApplicationContext(),this),SPLASH_DELAY);
            makeTransitionAnimation();
        }
    }
}
