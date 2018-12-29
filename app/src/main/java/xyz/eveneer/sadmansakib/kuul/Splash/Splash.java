package xyz.eveneer.sadmansakib.kuul.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.Tutorial.Tutorial;

import static xyz.eveneer.sadmansakib.kuul.Constants.splash.SPLASH_DELAY;

public class Splash extends AppCompatActivity {

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(this::launch,SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(this:: launch);
        super.onStop();
    }

    void launch() {
        if (!isFinishing()) {
            startActivity(new Intent(getApplicationContext(), Tutorial.class));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            finish();
        }
    }
}
