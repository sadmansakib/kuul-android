package xyz.eveneer.sadmansakib.kuul.Tutorial;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.TutorialActivity;

import xyz.eveneer.sadmansakib.kuul.R;

import static xyz.eveneer.sadmansakib.kuul.Constants.tutorial.PERMISSIONS;

public class TutorialViewModel extends ViewModel {

    public void setupview(TutorialActivity activity, Context context){
        activity.addFragment(new PermissionStep.Builder().setTitle(context.getString(R.string.app_name))
                .setContent(context.getString(R.string.tutorial_p1_content))
                .setBackgroundColor(context.getResources().getColor(R.color.bg_color)) // int background color
                .setDrawable(R.drawable.logo) // int top drawable
                .setPermissions(PERMISSIONS)
                .build());
    }
}
