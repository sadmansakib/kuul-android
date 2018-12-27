package xyz.eveneer.sadmansakib.kuul.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.model.Register;
import xyz.eveneer.sadmansakib.kuul.onboarding.Tutorial;

public class UserAuth extends AppCompatActivity {

    EditText gender,name,address;

    AppCompatButton loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        loginbtn = findViewById(R.id.login_button);
        gender=findViewById(R.id.gender);
        name= findViewById(R.id.name);
        address=findViewById(R.id.address);
    }

    public void login(View view) {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                Call<Register> call = Kuul.getClient().registerUser(
                        name.getText().toString(),
                        account.getPhoneNumber().toString(),
                        gender.getText().toString(),
                        address.getText().toString());
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if(response.body().getStatus().contains("success")){
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Tutorial.class));
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        }else{
                            Log.e("notregistered", "onResponse: "+response.body() );
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Log.e("fail", "onFailure: "+t );
                    }
                });
            }

            @Override
            public void onError(AccountKitError accountKitError) {
                Log.e("numbererror", "onError: "+accountKitError.toString() );
            }
        });


    }
}
