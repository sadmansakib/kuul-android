package xyz.eveneer.sadmansakib.kuul.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.home.Home;

public class Tutorial extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle(getString(R.string.app_name))
                .setContent(getString(R.string.first_fragment_content))
                .setBackgroundColor(Color.parseColor("#070707")) // int background color
                .setDrawable(R.drawable.logo) // int top drawable
                .build());
    }

    @Override
    public void currentFragmentPosition(int i) {

    }

    @Override
    public void finishTutorial() {
        super.finishTutorial();
        startActivity(new Intent(getApplicationContext(),Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
