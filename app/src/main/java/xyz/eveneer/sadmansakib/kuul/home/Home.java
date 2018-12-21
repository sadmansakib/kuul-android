package xyz.eveneer.sadmansakib.kuul.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.UI.CustomPrompt;

public class Home extends AppCompatActivity {

    AppCompatButton __help_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        __help_button=findViewById(R.id.help_button);
    }

    public void send_sos(View view) {
        CustomPrompt customPrompt= new CustomPrompt(Home.this);
        customPrompt.show();
    }
}
