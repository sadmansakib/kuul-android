package xyz.eveneer.sadmansakib.kuul.Splash;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.Tutorial.Tutorial;

class SplashViewModel extends ViewModel {

    void launch(Context context, Activity activity){
        context.startActivity(new Intent(context.getApplicationContext(),Tutorial.class));
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
