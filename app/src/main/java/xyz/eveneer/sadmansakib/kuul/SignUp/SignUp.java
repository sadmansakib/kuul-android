package xyz.eveneer.sadmansakib.kuul.SignUp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import xyz.eveneer.sadmansakib.kuul.R;

public class SignUp extends AppCompatActivity {

    private  SignUpViewModel signUpViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpViewModel=ViewModelProviders.of(this).get(SignUpViewModel.class);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        AppCompatButton loginbtn = findViewById(R.id.login_button);
        EditText gender=findViewById(R.id.gender);
        EditText name= findViewById(R.id.name);
        EditText address=findViewById(R.id.address);

        loginbtn.setOnClickListener(v ->
                signUpViewModel.getUserNumber().observe(this, number -> signUpViewModel.login(
                        this,
                name.getText().toString().trim(),
                number,
                gender.getText().toString().trim(),
                address.getText().toString().trim())
        ));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
