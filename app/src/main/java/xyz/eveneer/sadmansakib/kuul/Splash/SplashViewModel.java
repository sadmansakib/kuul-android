/*
 *
 *    Copyright 2018  Sadman Sakib
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package xyz.eveneer.sadmansakib.kuul.Splash;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.util.Objects;

import xyz.eveneer.sadmansakib.kuul.Home.Home;
import xyz.eveneer.sadmansakib.kuul.SignUp.SignUp;

import static xyz.eveneer.sadmansakib.kuul.Constants.otp.APP_REQUEST_CODE;

class SplashViewModel extends AndroidViewModel {

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    void launchOTP(Activity activity){
        final Intent intent =new Intent(getApplication().getApplicationContext(),AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        activity.startActivityForResult(intent,APP_REQUEST_CODE);
    }

    boolean checkOTPStatus(int requestCode, Intent data) {
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult =
                    Objects.requireNonNull(data)
                            .getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (loginResult.getError() != null){
                Toast.makeText(getApplication().getApplicationContext(),
                        String.valueOf(loginResult.getError()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
            else if(loginResult.wasCancelled()){
                Toast.makeText(getApplication().getApplicationContext(),
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

    boolean checkUserAlreadyLoggedIn() {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        return accessToken != null;
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

    void launchSignUp(Activity activity) {
        activity.startActivity(new Intent(getApplication().getApplicationContext(),SignUp.class));
    }

    void launchHome(Activity activity){
        activity.startActivity(new Intent(getApplication().getApplicationContext(),Home.class));
    }
}
