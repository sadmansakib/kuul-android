package xyz.eveneer.sadmansakib.kuul.SignUp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
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
