package xyz.eveneer.sadmansakib.kuul.Splash;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.util.Objects;

import xyz.eveneer.sadmansakib.kuul.SignUp.SignUp;

import static xyz.eveneer.sadmansakib.kuul.Constants.otp.APP_REQUEST_CODE;

class SplashViewModel extends ViewModel {

    void launchOTP(Context context, Activity activity){
        final Intent intent =new Intent(context,AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        activity.startActivityForResult(intent,APP_REQUEST_CODE);
    }

    boolean checkOTPStatus(Context context, int requestCode, Intent data) {
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult =
                    Objects.requireNonNull(data)
                            .getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (loginResult.getError() != null){
                Toast.makeText(context,
                        String.valueOf(loginResult.getError()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
            else if(loginResult.wasCancelled()){
                Toast.makeText(context,
                        "Login was cancelled",
                        Toast.LENGTH_LONG).show();
                return false;
            }
            else if (loginResult.getAccessToken() != null){
                getCurrentUserPhoneNumber();
                return true;
            }
        }
        return false;
    }

    private void getCurrentUserPhoneNumber() {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }

    void launchSignUp(Context applicationContext, Activity activity) {
        activity.startActivity(new Intent(applicationContext,SignUp.class));
    }
}
