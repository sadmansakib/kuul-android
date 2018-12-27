package xyz.eveneer.sadmansakib.kuul.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
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

import org.json.JSONObject;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.auth.UserAuth;
import xyz.eveneer.sadmansakib.kuul.home.Home;
import xyz.eveneer.sadmansakib.kuul.model.UserExistanceCheck;

public class Splash extends AppCompatActivity {

    String toastMessage;
    private final static int APP_REQUEST_CODE= 99;

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
                    startLoginPage();
//                }
            }
        },1000);
    }

    private void startLoginPage() {
        final Intent intent =new Intent(getApplicationContext(),AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        startActivityForResult(intent,APP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = Objects.requireNonNull(data).getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            Log.d("loginresult", "onActivityResult: "+loginResult.getAccessToken());
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                showErrorActivity(loginResult.getError());
            }
            else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            }
            else if (loginResult.getAccessToken() != null){
                toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
            }
            else {
                toastMessage = String.format(
                            "Success:%s...",
                            Objects.requireNonNull(loginResult.getAuthorizationCode()).substring(0,10));
                }
            }

            Log.d("oAuth", "onActivityResult: "+toastMessage);

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
//                Log.d("number", "onSuccess: "+account.getPhoneNumber().toString());
                checkUserExistance(account.getPhoneNumber().toString());
            }

            @Override
            public void onError(AccountKitError accountKitError) {
                Log.e("numbererror", "onError: "+accountKitError.toString() );
            }
        });
        }

    private void checkUserExistance(String phone_number) {
        try{
            Call<UserExistanceCheck> call = Kuul.getClient().checkUser(phone_number);
            call.enqueue(new Callback<UserExistanceCheck>() {
                @Override
                public void onResponse(Call<UserExistanceCheck> call, Response<UserExistanceCheck> response) {
                   Boolean status = response.body().getStatus();
                    Log.d("mairechudi", "onResponse: "+status);
                   if(status!=null){
                       goHome();

                   }else{
                       gotoUserAuth();
                   }
                }

                @Override
                public void onFailure(Call<UserExistanceCheck> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Log.e("error", "checkUserExistance: "+e);
        }
    }

    private void goHome() {
        startActivity(new Intent(this,Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void gotoUserAuth() {
        startActivity(new Intent(this,UserAuth.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void showErrorActivity(AccountKitError error) {
        Toast.makeText(this,String.valueOf(error),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
