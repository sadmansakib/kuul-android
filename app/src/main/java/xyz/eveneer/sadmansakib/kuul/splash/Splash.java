package xyz.eveneer.sadmansakib.kuul.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.home.Home;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*
          Transparent status bar for splash screen
          **/
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        goNextStep();
    }

    private void goNextStep() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Home.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        },1000);
    }
}
