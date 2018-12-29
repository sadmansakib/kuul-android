package xyz.eveneer.sadmansakib.kuul.Tutorial;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import com.hololo.tutorial.library.TutorialActivity;

import xyz.eveneer.sadmansakib.kuul.Home.Home;

public class Tutorial extends TutorialActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TutorialViewModel tutorialViewModel = ViewModelProviders.of(this).get(TutorialViewModel.class);
        tutorialViewModel.setupview(this,getApplicationContext());
    }

    /** Useless method for this project. This method shouldn't be removed or this class shouldn't be an abstract class **/
    @Override
    public void currentFragmentPosition(int i) {

    }

    @Override
    public void finishTutorial() {
        super.finishTutorial();
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }
}
