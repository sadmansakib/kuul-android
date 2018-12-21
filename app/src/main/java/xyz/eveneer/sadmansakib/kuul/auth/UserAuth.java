package xyz.eveneer.sadmansakib.kuul.auth;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.util.Objects;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.onboarding.Tutorial;

public class UserAuth extends AppCompatActivity {

    private final static int REQUEST_CODE= 666;

    AppCompatButton loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        loginbtn=findViewById(R.id.login_button);

    }

    public void login(View view) {
        startLoginPage();
    }

    private void startLoginPage() {
        Intent intent =new Intent(getApplicationContext(),AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            AccountKitLoginResult result = Objects.requireNonNull(data).getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (result.getError()!= null){
                Toast.makeText(getApplicationContext(),""+result.getError().getErrorType().getMessage()
                ,Toast.LENGTH_LONG).show();
            }

            else if(result.wasCancelled()){
                Toast.makeText(getApplicationContext(),"Login cancelled"
                        ,Toast.LENGTH_LONG).show();
            }
            else{
                Log.d("oAUTH", "onActivityResult: Success! %s"+Objects.requireNonNull(result.getAuthorizationCode()).substring(0,10));
                startActivity(new Intent(getApplicationContext(),Tutorial.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
