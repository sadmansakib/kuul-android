package xyz.eveneer.sadmansakib.kuul.TrustedContacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import xyz.eveneer.sadmansakib.kuul.R;

public class Trusted_Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted__contact);
    }

    public void addMyContacts(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        builder.setView(inflater.inflate(R.layout.number_input_form, null))
                .setPositiveButton("ADD CONTACT", (dialog, which) -> {
                })
                .setNeutralButton("CANCEL", (dialog, which) -> {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }
}
