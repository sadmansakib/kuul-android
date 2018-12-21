package xyz.eveneer.sadmansakib.kuul.UI;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import xyz.eveneer.sadmansakib.kuul.R;

import static android.content.ContentValues.TAG;

public class CustomPrompt extends Dialog implements View.OnClickListener {

    private Activity current_activity;
    public Dialog dialog;
    private AppCompatButton __callme, __textme;

    public CustomPrompt(Activity activity){
        super(activity);
        this.current_activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.prompt_layout);
        __callme=findViewById(R.id.call_button);
        __textme=findViewById(R.id.message_button);
        __callme.setOnClickListener(this);
        __textme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_button:
                Toast.makeText(getContext(),"You will be notified via call",Toast.LENGTH_LONG).show();
                dismiss();
                break;
            case R.id.message_button:
                Toast.makeText(getContext(),"You will be notified via message",Toast.LENGTH_LONG).show();
                dismiss();
            default:
                Log.d(TAG, "onClick: ");
        }
    }
}
