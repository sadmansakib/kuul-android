package xyz.eveneer.sadmansakib.kuul.Tutorial;

import android.content.Intent;
import android.os.Bundle;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.TutorialActivity;

import xyz.eveneer.sadmansakib.kuul.Home.Home;
import xyz.eveneer.sadmansakib.kuul.R;

import static xyz.eveneer.sadmansakib.kuul.Constants.tutorial.PERMISSIONS;

public class Tutorial extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addFragment(new PermissionStep.Builder().setTitle(getString(R.string.app_name))
                .setContent(getString(R.string.tutorial_p1_content))
                .setBackgroundColor(getResources().getColor(R.color.bg_color)) // int background color
                .setDrawable(R.drawable.logo) // int top drawable
                .setPermissions(PERMISSIONS)
                .build());
    }

    /** Useless method for this project. This method shouldn't be removed or this class shouldn't be an abstract class**/
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
